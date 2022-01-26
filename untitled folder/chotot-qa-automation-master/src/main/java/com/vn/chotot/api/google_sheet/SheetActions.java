package com.vn.chotot.api.google_sheet;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.*;
import com.vn.chotot.logger.Log4jFactory;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.vn.chotot.keywords.selenium.Utils.delayStep;

public class SheetActions {

    private static SheetActions sheetActions;
    final Logger log = Log4jFactory.instance().createClassLogger(getClass());

    private SheetActions() {
    }

    public static synchronized SheetActions getInstance() {
        if (sheetActions == null) sheetActions = new SheetActions();
        return sheetActions;
    }

    private Sheets getSheetService() {
        if (SheetService.instance().getService() == null) {
            SheetService.instance().createGoogleSheetService();
        }
        return SheetService.instance().getService();
    }

    public String create(String title) throws IOException {
        Sheets service = getSheetService();

        Spreadsheet spreadsheet =
                new Spreadsheet().setProperties(new SpreadsheetProperties().setTitle(title));

        spreadsheet = service.spreadsheets().create(spreadsheet).setFields("spreadsheetId").execute();

        log.info("Spreadsheet ID: " + spreadsheet.getSpreadsheetId());
        return spreadsheet.getSpreadsheetId();
    }

    public BatchUpdateSpreadsheetResponse batchUpdate(
            String spreadsheetId, String title, String find, String replacement) throws IOException {
        Sheets service = getSheetService();

        List<Request> requests = new ArrayList<>();
        // Change the spreadsheet's title.
        requests.add(
                new Request()
                        .setUpdateSpreadsheetProperties(
                                new UpdateSpreadsheetPropertiesRequest()
                                        .setProperties(new SpreadsheetProperties().setTitle(title))
                                        .setFields("title")));
        // Find and replace text.
        requests.add(
                new Request()
                        .setFindReplace(
                                new FindReplaceRequest()
                                        .setFind(find)
                                        .setReplacement(replacement)
                                        .setAllSheets(true)));

        BatchUpdateSpreadsheetRequest body = new BatchUpdateSpreadsheetRequest().setRequests(requests);

        BatchUpdateSpreadsheetResponse response =
                service.spreadsheets().batchUpdate(spreadsheetId, body).execute();

        FindReplaceResponse findReplaceResponse = response.getReplies().get(1).getFindReplace();

        log.info(findReplaceResponse.getOccurrencesChanged() + " replacements made.");
        return response;
    }

    public ValueRange getValues(String spreadsheetId, String range) {
        Sheets service = getSheetService();
        ValueRange result;
        try {
            result = service.spreadsheets().values().get(spreadsheetId, range).execute();
            int numRows = result.getValues() != null ? result.getValues().size() : 0;

            // log.info(numRows + " rows retrieved.");
            return result;
        } catch (IOException e) {
            log.error(e.getMessage());
        }

        return null;
    }


    public BatchGetValuesResponse batchGetValues(String spreadsheetId, List<String> _ranges)
            throws IOException {
        Sheets service = getSheetService();

        // log.info(result.getValueRanges().size() + " ranges retrieved.");
        return service.spreadsheets().values().batchGet(spreadsheetId).setRanges(_ranges).execute();
    }

    public UpdateValuesResponse updateValues(
            String spreadsheetId, String range, String valueInputOption, List<List<Object>> _values) {
        try {
            Sheets service = getSheetService();

            ValueRange body = new ValueRange().setValues(_values);

            // log.info(result.getUpdatedCells() + " cells updated.");
            return service
                    .spreadsheets()
                    .values()
                    .update(spreadsheetId, range, body)
                    .setValueInputOption(valueInputOption)
                    .execute();
        } catch (IOException e) {
            log.error(e.getMessage());
        }

        return null;
    }

    public BatchUpdateValuesResponse batchUpdateValues(
            String spreadsheetId, String range, String valueInputOption, List<List<Object>> _values) {
        Sheets service = getSheetService();

        List<ValueRange> data = new ArrayList<>();
        data.add(new ValueRange().setRange(range).setValues(_values));

        BatchUpdateValuesRequest body =
                new BatchUpdateValuesRequest().setValueInputOption(valueInputOption).setData(data);
        delayStep(1);
        try {
            // log.info(result.getTotalUpdatedCells() + " cells batched updates.");
            return service.spreadsheets().values().batchUpdate(spreadsheetId, body).execute();
        } catch (IOException e) {
            log.error(e.getMessage());
        }

        return null;
    }

    public AppendValuesResponse appendValues(
            String spreadsheetId, String range, String valueInputOption, List<List<Object>> _values) {
        Sheets service = getSheetService();

        ValueRange body = new ValueRange().setValues(_values);

        try {
            AppendValuesResponse result =
                    service
                            .spreadsheets()
                            .values()
                            .append(spreadsheetId, range, body)
                            .setValueInputOption(valueInputOption)
                            .execute();
             delayStep(1);
            // log.info(result.getUpdates().getUpdatedCells() + " cells appended.");
            return result;
        } catch (IOException e) {
            log.error(e.getMessage());
        }

        return null;
    }

    public BatchUpdateSpreadsheetResponse conditionalFormat(String spreadsheetId) throws IOException {

        List<GridRange> ranges =
                Collections.singletonList(
                        new GridRange()
                                .setSheetId(0)
                                .setStartRowIndex(1)
                                .setEndRowIndex(11)
                                .setStartColumnIndex(0)
                                .setEndColumnIndex(4));

        List<Request> requests =
                Arrays.asList(
                        new Request()
                                .setAddConditionalFormatRule(
                                        new AddConditionalFormatRuleRequest()
                                                .setRule(
                                                        new ConditionalFormatRule()
                                                                .setRanges(ranges)
                                                                .setBooleanRule(
                                                                        new BooleanRule()
                                                                                .setCondition(
                                                                                        new BooleanCondition()
                                                                                                .setType("CUSTOM_FORMULA")
                                                                                                .setValues(
                                                                                                        Collections.singletonList(
                                                                                                                new ConditionValue()
                                                                                                                        .setUserEnteredValue(
                                                                                                                                "=GT($D2,median($D$2:$D$11))"))))
                                                                                .setFormat(
                                                                                        new CellFormat()
                                                                                                .setTextFormat(
                                                                                                        new TextFormat()
                                                                                                                .setForegroundColor(
                                                                                                                        new Color().setRed(0.8f))))))
                                                .setIndex(0)),
                        new Request()
                                .setAddConditionalFormatRule(
                                        new AddConditionalFormatRuleRequest()
                                                .setRule(
                                                        new ConditionalFormatRule()
                                                                .setRanges(ranges)
                                                                .setBooleanRule(
                                                                        new BooleanRule()
                                                                                .setCondition(
                                                                                        new BooleanCondition()
                                                                                                .setType("CUSTOM_FORMULA")
                                                                                                .setValues(
                                                                                                        Collections.singletonList(
                                                                                                                new ConditionValue()
                                                                                                                        .setUserEnteredValue(
                                                                                                                                "=LT($D2,median($D$2:$D$11))"))))
                                                                                .setFormat(
                                                                                        new CellFormat()
                                                                                                .setBackgroundColor(
                                                                                                        new Color()
                                                                                                                .setRed(1f)
                                                                                                                .setGreen(0.4f)
                                                                                                                .setBlue(0.4f)))))
                                                .setIndex(0)));

        BatchUpdateSpreadsheetRequest body = new BatchUpdateSpreadsheetRequest().setRequests(requests);
        BatchUpdateSpreadsheetResponse result =
                getSheetService().spreadsheets().batchUpdate(spreadsheetId, body).execute();

        log.info(result.getReplies().size() + " cells updated format.");
        return result;
    }

    public BatchUpdateSpreadsheetResponse batchUpdateNote(String spreadsheetID, String sheetID, int startRowIndex
                                                        , int endRowIndex, int startColumnIndex, int endColumnIndex, String note) {
        Sheets service = getSheetService();
        List<Request> requests = new ArrayList<>();
        requests.add(new Request().setRepeatCell(new RepeatCellRequest()
                .setRange(new GridRange()
                        .setSheetId(Integer.valueOf(sheetID))
                        .setStartRowIndex(startRowIndex)
                        .setEndRowIndex(endRowIndex)
                        .setStartColumnIndex(startColumnIndex)
                        .setEndColumnIndex(endColumnIndex)).setCell(new CellData().setNote(note)).setFields("note")));

        BatchUpdateSpreadsheetRequest body = new BatchUpdateSpreadsheetRequest().setRequests(requests);
        try {
            return service.spreadsheets().batchUpdate(spreadsheetID, body).execute();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    public void insertNewEmptyColumn(
            String spreadsheetId, String sheetId, int startColumnIndex, int endColumnIndex) {
        DimensionRange dimRange = new DimensionRange();
        dimRange.setStartIndex(startColumnIndex);
        dimRange.setEndIndex(endColumnIndex);
        dimRange.setSheetId(Integer.valueOf(sheetId));
        dimRange.setDimension("COLUMNS");

        InsertDimensionRequest insertDimensionRequest = new InsertDimensionRequest();
        insertDimensionRequest.setRange(dimRange);
        insertDimensionRequest.setInheritFromBefore(false);

        List<Request> requests = new ArrayList<>();
        requests.add(new Request().setInsertDimension(insertDimensionRequest));

        BatchUpdateSpreadsheetRequest batchRequests = new BatchUpdateSpreadsheetRequest();
        batchRequests.setRequests(requests);

        try {
            getSheetService().spreadsheets().batchUpdate(spreadsheetId, batchRequests).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
