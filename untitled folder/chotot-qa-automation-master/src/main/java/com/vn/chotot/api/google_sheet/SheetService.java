package com.vn.chotot.api.google_sheet;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.vn.chotot.logger.Log4jFactory;
import org.apache.commons.collections4.map.LinkedMap;
import org.apache.logging.log4j.Logger;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

import static com.vn.chotot.configuration.Constant.GG_TOKEN;

public class SheetService {
    private static final String APPLICATION_NAME = "Google Sheets API";
    private static SheetService sheetService;
    final Logger log = Log4jFactory.instance().createClassLogger(getClass());
    private final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private final List<String> SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS);
    private final LinkedMap<Long, Sheets> sheetStorage = new LinkedMap<>();

    private SheetService() { }

    public static synchronized SheetService instance() {
        if (sheetService == null) sheetService = new SheetService();
        return sheetService;
    }

    public void createGoogleSheetService() {
        byte[] decodedBytes = Base64.getDecoder().decode(GG_TOKEN);
        InputStream in = new ByteArrayInputStream(decodedBytes);

        try {
            NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            GoogleCredential credential = GoogleCredential
                    .fromStream(in)
                    .createScoped(SCOPES);
            Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                    .setApplicationName(APPLICATION_NAME)
                    .build();

            // Store service
            setSheetServiceStorage(service);

        } catch (IOException | GeneralSecurityException e) {
            e.printStackTrace();
        }
    }

    public void createSheetService() {
        try {
            NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            // Load client secrets.
            String CREDENTIALS_FILE_PATH = "/credentials.json";
            InputStream in = SheetService.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
            GoogleClientSecrets clientSecrets =
                    GoogleClientSecrets.load(this.JSON_FACTORY, new InputStreamReader(in));

            // Build flow and trigger user authorization request.
            String TOKENS_DIRECTORY_PATH = "tokens";
            GoogleAuthorizationCodeFlow flow =
                    new GoogleAuthorizationCodeFlow.Builder(
                            HTTP_TRANSPORT, this.JSON_FACTORY, clientSecrets, this.SCOPES)
                            .setDataStoreFactory(
                                    new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                            .setAccessType("offline")
                            .build();
            LocalServerReceiver receiver = new LocalServerReceiver();
            Credential credential = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");

            // Build a new authorized API client service.
            Sheets service =
                    new Sheets.Builder(HTTP_TRANSPORT, this.JSON_FACTORY, credential)
                            .setApplicationName(APPLICATION_NAME)
                            .build();

            // Store service
            setSheetServiceStorage(service);

        } catch (IOException | GeneralSecurityException e1) {
            log.error(e1.getMessage());
        }
    }

    private void setSheetServiceStorage(Sheets sheet) {
        sheetStorage.put(Thread.currentThread().getId(), sheet);
    }

    public Sheets getService() {
        if (sheetStorage.size() == 0) {
            return null;
        }

        if (sheetStorage.containsKey(Thread.currentThread().getId()))
            return sheetStorage.get(Thread.currentThread().getId());

        return sheetStorage.getValue(0);
    }
}
