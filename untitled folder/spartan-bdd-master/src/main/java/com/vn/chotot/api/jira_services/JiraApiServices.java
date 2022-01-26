package com.vn.chotot.api.jira_services;

import com.jayway.jsonpath.JsonPath;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ResponseBody;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static io.restassured.RestAssured.given;

public class JiraApiServices {
    private final String TOKEN;

    public JiraApiServices() {
        RestAssured.baseURI = JiraApiConfigs.getBaseUri();
        RestAssured.basePath = JiraApiConfigs.getBasePath();
        TOKEN = JiraApiConfigs.get_TOKEN();
    }

    public List<String> getResponseDataList(ResponseBody responseBody, String jsonPathQuery) {
        return JsonPath.parse(responseBody.asString()).read(jsonPathQuery);
    }

    public String getResponseData(ResponseBody responseBody, String jsonPathQuery) {
        return JsonPath.parse(responseBody.asString()).read(jsonPathQuery);
    }

    // ======================== ACTIONS ON JIRA ========================

    public void createAndLinkNewIssue(
            String projKey,
            String storyKey,
            String summary,
            String desc,
            String assignee,
            String extInfo,
            String imagePath) {
        List<String> issueSummaryList = getAllIssueSummaryOfStory(storyKey);
        List<String> issueKeyList = getAllIssueKeysOfStory(storyKey);

        // 1. When Summary doesn't exist, creating a new issue
        if (!issueSummaryList.contains(summary)) {
            desc += "\\n*Information:* " + extInfo; // Add ext_infor to Issue. (Information of comment)
            String newIssueKey = createNewIssue(projKey, summary, desc, assignee);
            linkIssueToIssue(newIssueKey, storyKey);
            updateIssueStatusToNew(newIssueKey);
            attachImageToIssue(newIssueKey, imagePath);
        } else {
            String comment = summary + "\\n" + desc;
            String issueKeyToComment = issueKeyList.get(issueSummaryList.indexOf(summary.trim()));
            commentIssueAttachImage(issueKeyToComment, assignee, comment, extInfo, imagePath);
        }
    }

    private String createNewIssue(String projKey, String summary, String desc, String assignee) {
        JiraApiConfigs.set_CreateIssueJson(projKey, summary, desc, assignee);
        String issueKey =
                given()
                        .header("Authorization", "Basic " + TOKEN)
                        .accept("application/json")
                        .contentType(ContentType.JSON)
                        .body(JiraApiConfigs.get_CreateIssueJson())
                        .when()
                        .post("/issue")
                        .then()
                        .statusCode(201)
                        .extract()
                        .response()
                        .jsonPath()
                        .get("key");
        System.out.printf("Issue[%s] is created\n", issueKey);
        return issueKey;
    }

    private String createNewStory(String projKey, String summary, String desc, String assignee) {
        JiraApiConfigs.set_CreateStoryJson(projKey, summary, desc, assignee);
        String storyKey =
                given()
                        .header("Authorization", "Basic " + TOKEN)
                        .accept("application/json")
                        .contentType(ContentType.JSON)
                        .body(JiraApiConfigs.get_CreateStoryJson())
                        .when()
                        .post("/issue")
                        .then()
                        .statusCode(201)
                        .extract()
                        .response()
                        .jsonPath()
                        .get("key");
        System.out.printf("Story[%s] is created with summary [%s]\n", storyKey, summary);
        return storyKey;
    }

    private void linkIssueToIssue(String srcIssueKey, String destIssueKey) {
        JiraApiConfigs.set_UpdateLinkIssueJson(destIssueKey);
        String urlPath = "/issue/" + srcIssueKey;
        given()
                .header("Authorization", "Basic " + TOKEN)
                .contentType(ContentType.JSON)
                .body(JiraApiConfigs.get_UpdateLinkIssueJson())
                .when()
                .put(urlPath)
                .then()
                .statusCode(204);
        System.out.printf("Issue[%s] is linked to [%s]\n", srcIssueKey, destIssueKey);
    }

    private void updateIssueStatusToNew(String issueKey) {
        String urlPath = String.format("/issue/%s/transitions", issueKey);
        given()
                .header("Authorization", "Basic " + TOKEN)
                .contentType(ContentType.JSON)
                .body(JiraApiConfigs.get_UpdateStatusNewJson())
                .when()
                .post(urlPath)
                .then()
                .statusCode(204);
        System.out.printf("Issue[%s] is updated to New Request\n", issueKey);
    }

    private void attachImageToIssue(String issueKey, String imagePath) {
        File fileUpload = new File(imagePath);
        String urlPath = String.format("/issue/%s/attachments", issueKey);

        given()
                .header("X-Atlassian-Token", "nocheck")
                .header("Authorization", "Basic " + TOKEN)
                .accept("application/json")
                .multiPart("file", fileUpload, "image/jpeg")
                .when()
                .post(urlPath)
                .then()
                .statusCode(200);
        System.out.printf("Attached an Image to issue[%s]\n", issueKey);
    }

    @Deprecated
    public void commentIssue(String issueKey, String tagUser, String issueDetail, String modeName) {
        String executeDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
        JiraApiConfigs.set_IssueCommentJson(tagUser, issueDetail, modeName, executeDate);

        String urlPath = "/issue/" + issueKey;
        given()
                .header("Authorization", "Basic " + TOKEN)
                .contentType(ContentType.JSON)
                .body(JiraApiConfigs.get_IssueCommentJson())
                .when()
                .put(urlPath)
                .then()
                .statusCode(204)
                .extract()
                .response()
                .body();

        System.out.printf("Added a comment on Issue[%s]\n", issueKey);
    }

    public void commentIssueAttachImage(
            String issueKey, String tagUser, String issueDetail, String modeName, String imagePath) {
        // 1. Initialize
        String imageName = imagePath.split("/")[imagePath.split("/").length - 1];
        String executeDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
        JiraApiConfigs.set_IssueCommentImageJson(
                tagUser, issueDetail, imageName, modeName, executeDate);

        // 2. Upload Image to Issue
        attachImageToIssue(issueKey, imagePath);

        // 3. Comment to Issue & attach Image
        String urlPath = "/issue/" + issueKey;
        given()
                .header("Authorization", "Basic " + TOKEN)
                .contentType(ContentType.JSON)
                .body(JiraApiConfigs.get_IssueCommentImageJson())
                .when()
                .put(urlPath)
                .then()
                .statusCode(204);
        System.out.printf("Added a comment onto Issue[%s] with Image[%s]\n", issueKey, imageName);
    }

    // ======================== GET DATA FROM JIRA ========================

    public String getStoryKey(String projKey, String storySummary, String desc, String assignee) {
        List<String> storyKeysList = getAllStoryKeysOfProject(projKey);
        List<String> storySummaryList = getAllStorySummaryOfProject(projKey);

        if (!storySummaryList.contains(storySummary)) {
            return createNewStory(projKey, storySummary, desc, assignee);
        } else {
            String curStoryKey = storyKeysList.get(storySummaryList.indexOf(storySummary));
            System.out.printf("Story[%s] exists as [%s]\n", curStoryKey, storySummary);
            return curStoryKey;
        }
    }

    private List<String> getAllStoryKeysOfProject(String projKey) {
        String urlPath = String.format("/search?jql=project=%s&maxResults=9000", projKey);
        ResponseBody responseBody =
                given()
                        .header("Authorization", "Basic " + TOKEN)
                        .when()
                        .get(urlPath)
                        .then()
                        .statusCode(200)
                        .extract()
                        .response()
                        .body();

        return getResponseDataList(
                responseBody,
                "$.issues[?(@.fields.labels[0]=='Automation_Team' && @.fields.issuetype.id=='10002')].key");
    }

    private List<String> getAllStorySummaryOfProject(String projKey) {
        String urlPath = String.format("/search?jql=project=%s&maxResults=9000", projKey);
        ResponseBody responseBody =
                given()
                        .header("Authorization", "Basic " + TOKEN)
                        .when()
                        .get(urlPath)
                        .then()
                        .statusCode(200)
                        .extract()
                        .response()
                        .body();

        return getResponseDataList(
                responseBody,
                "$.issues[?(@.fields.labels[0]=='Automation_Team' && @.fields.issuetype.id=='10002')].fields.summary");
    }

    private List<String> getAllIssueSummaryOfProject(String projKey) {
        String urlPath =
                String.format(
                        "/search?jql=project=\"%s\"&maxResults=9000" + "&fields=key,labels,summary", projKey);
        ResponseBody responseBody =
                given()
                        .header("Authorization", "Basic " + TOKEN)
                        .when()
                        .get(urlPath)
                        .then()
                        .statusCode(200)
                        .extract()
                        .response()
                        .body();

        return getResponseDataList(
                responseBody, "$.issues[?(@.fields.labels[0]=='Automation_Team')].fields.summary");
    }

    private List<String> getAllIssueKeysOfProject(String projKey) {
        String urlPath =
                String.format(
                        "/search?jql=project=\"%s\"&maxResults=9000" + "&fields=key,labels,summary", projKey);
        ResponseBody responseBody =
                given()
                        .header("Authorization", "Basic " + TOKEN)
                        .when()
                        .get(urlPath)
                        .then()
                        .statusCode(200)
                        .extract()
                        .response()
                        .body();

        return getResponseDataList(
                responseBody, "$.issues[?(@.fields.labels[0]=='Automation_Team')].key");
    }

    private List<String> getAllIssueSummaryOfStory(String storyKey) {
        String urlPath = "/issue/" + storyKey;
        String query =
                "$.fields.issuelinks[*]"
                        + "[?(@.type.outward=='is child of'"
                        + " && @.inwardIssue.fields.issuetype.id=='10004')]"
                        + ".inwardIssue.fields.summary";

        ResponseBody responseBody =
                given()
                        .header("Authorization", "Basic " + TOKEN)
                        .when()
                        .get(urlPath)
                        .then()
                        .statusCode(200)
                        .extract()
                        .response()
                        .body();

        return getResponseDataList(responseBody, query);
    }

    private List<String> getAllIssueDescriptionOfStory(String storyKey) {
        String urlPath = "/issue/" + storyKey;
        String query =
                "$.fields.issuelinks[*]"
                        + "[?(@.type.outward=='is child of'"
                        + " && @.inwardIssue.fields.issuetype.id=='10004')]"
                        +
                        //                ".inwardIssue.fields.summary";
                        ".inwardIssue.key";

        ResponseBody responseBody =
                given()
                        .header("Authorization", "Basic " + TOKEN)
                        .when()
                        .get(urlPath)
                        .then()
                        .statusCode(200)
                        .extract()
                        .response()
                        .body();

        List<String> issueSummaryList = getResponseDataList(responseBody, query);
        String params = "";
        StringBuilder issueKeyParam = new StringBuilder();
        for (String issueSummary : issueSummaryList) {
            issueKeyParam.append(issueSummary).append(",");
        }
        issueKeyParam = new StringBuilder(issueKeyParam.substring(0, issueKeyParam.length() - 1));

        // Get all description of issue linking to story
        JiraApiConfigs.set_searchJQL("project=GAM", "issuetype=Bug", "key in (" + issueKeyParam + ")");
        urlPath = "/search";
        query = "$.issues[*].fields.description";

        responseBody =
                given()
                        .header("Authorization", "Basic " + TOKEN)
                        .queryParam("jql", JiraApiConfigs.get_searchJQL())
                        .when()
                        .get(urlPath)
                        .then()
                        .statusCode(200)
                        .extract()
                        .response()
                        .body();
        return getResponseDataList(responseBody, query);
    }

    private List<String> getAllIssueKeysOfStory(String storyKey) {
        String urlPath = "/issue/" + storyKey;
        String query =
                "$.fields.issuelinks[*]"
                        + "[?(@.type.outward=='is child of'"
                        + " && @.inwardIssue.fields.issuetype.id=='10004')]"
                        + ".inwardIssue.key";

        ResponseBody responseBody =
                given()
                        .header("Authorization", "Basic " + TOKEN)
                        .when()
                        .get(urlPath)
                        .then()
                        .statusCode(200)
                        .extract()
                        .response()
                        .body();

        return getResponseDataList(responseBody, query);
    }
}
