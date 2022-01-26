package com.vn.chotot.api.jira_services;

import java.util.Base64;

public class JiraApiConfigs {

    private static final String USERNAME = "vuhoang" + "@chotot.vn";
    private static final String USERTOKEN = "i4iKkuwOfIgb7WhGoYFAB168";
    private static final String BASE_URI = "https://701search.atlassian.net";
    private static final String BASE_PATH = "/rest/api/2";
    // priority(ID/NAME)   -> 1/Highest  2/High  3/Medium    4/Low   5/Lowest
    // issuetype(ID/NAME)  ->
    private static String CREATE_ISSUE =
            "{\n"
                    + "  \"fields\": {\n"
                    + "    \"project\": {\n"
                    + "      \"key\": \"%s\"\n"
                    + "    },\n"
                    + "    \"summary\": \"%s\",\n"
                    +
                    //                "    \"description\": \"%s\",\n" +
                    "    \"description\": \"{panel:bgColor=#ffebe6}\\n%s\\n{panel}\",\n"
                    + "    \"assignee\": {\n"
                    + "      \"name\": \"%s\"\n"
                    + "    },\n"
                    + "    \"priority\": {\n"
                    + "      \"id\": \"3\",\n"
                    + "      \"name\": \"Medium\"\n"
                    + "    },\n"
                    + "    \"issuetype\": {\n"
                    + "      \"id\": \"10004\",\n"
                    + "      \"name\": \"Bug\"\n"
                    + "    },\n"
                    + "    \"labels\": [\n"
                    + "      \"Automation_Team\"\n"
                    + "    ]\n"
                    + "  }\n"
                    + "}";

    private static String UPDATE_LINK_ISSUE =
            "{\n"
                    + "  \"update\": {\n"
                    + "    \"issuelinks\": [\n"
                    + "      {\n"
                    + "        \"add\": {\n"
                    + "          \"type\": {\n"
                    + "            \"id\": \"10400\",\n"
                    + "            \"name\": \"Parent Child\",\n"
                    + "            \"inward\": \"is parent of\",\n"
                    + "            \"outward\": \"is child of\"\n"
                    + "          },\n"
                    + "          \"outwardIssue\": {\n"
                    + "            \"key\": \"%s\"\n"
                    + "          }\n"
                    + "        }\n"
                    + "      }\n"
                    + "    ]\n"
                    + "  }\n"
                    + "}";

    private static String UPDATE_ISSUE_COMMENT_NEW =
            "{\n"
                    + "  \"update\": {\n"
                    + "    \"comment\": [\n"
                    + "      {\n"
                    + "        \"add\": {\n"
                    + "          \"body\": \"Hi [~%s] ,\\n\\n"
                    + "Please help to check this problem:\\n\\n"
                    + "{color:#ff5630}%s{color}\\n\\n"
                    + "----\\n\\n"
                    + "*MODE:* %s\\n\\n"
                    + "*Executed Date:* %s\\n\\n"
                    + "*Reporter:* Automation QC Team\"\n"
                    + "        }\n"
                    + "      }\n"
                    + "    ]\n"
                    + "  }\n"
                    + "}";

    private static String UPDATE_ISSUE_COMMENT_IMAGE =
            "{\n"
                    + "  \"update\": {\n"
                    + "    \"comment\": [\n"
                    + "      {\n"
                    + "        \"add\": {\n"
                    + "          \"body\": \"Hi [~%s],\\n\\n"
                    + "Please help to check this problem:\\n\\n"
                    + "{panel:bgColor=#ffebe6}\\n"
                    + "{color:#ff5630}"
                    + "%s"
                    + "{color}"
                    + "\\n"
                    + "!%s|width=512,height=298!\\n"
                    + "{panel}\\n"
                    + "{panel:bgColor=#deebff}\\n"
                    + "*MODE:* %s\\n\\n"
                    + "*Executed Date:* %s\\n\\n"
                    + "*Reporter:* Automation QC Team"
                    + "\\n{panel}\"\n"
                    + "        }\n"
                    + "      }\n"
                    + "    ]\n"
                    + "  }\n"
                    + "}";

    private static String CREATE_STORY =
            "{\n"
                    + "  \"fields\": {\n"
                    + "    \"project\": {\n"
                    + "      \"key\": \"%s\"\n"
                    + "    },\n"
                    + "    \"summary\": \"%s\",\n"
                    + "    \"description\": \"%s\",\n"
                    + "    \"assignee\": {\n"
                    + "      \"name\": \"%s\"\n"
                    + "    },\n"
                    + "    \"priority\": {\n"
                    + "      \"id\": \"3\",\n"
                    + "      \"name\": \"Medium\"\n"
                    + "    },\n"
                    + "    \"issuetype\": {\n"
                    + "      \"id\": \"10002\",\n"
                    + "      \"name\": \"Story\"\n"
                    + "    },\n"
                    + "    \"labels\": [\n"
                    + "      \"Automation_Team\"\n"
                    + "    ]\n"
                    + "  }\n"
                    + "}";

    private static String searchJQL = "";

    public static String get_searchJQL() {
        return searchJQL;
    }

    public static void set_searchJQL(String... params) {
        for (int i = 0; i < params.length; i++) {
            if (i == 0) JiraApiConfigs.searchJQL += params[i];
            else JiraApiConfigs.searchJQL += " AND " + params[i];
        }
    }

    public static String getBaseUri() {
        return BASE_URI;
    }

    public static String getBasePath() {
        return BASE_PATH;
    }

    public static String get_CreateIssueJson() {
        return CREATE_ISSUE;
    }

    public static void set_CreateIssueJson(
            String projKey, String summary, String desc, String assignee) {
        desc = desc.replace("\n", "\\n").replace("\"", "'");
        summary = summary.replace("\n", "\\n").replace("\"", "'");
        CREATE_ISSUE = String.format(CREATE_ISSUE, projKey, summary, desc, assignee);
    }

    public static String get_UpdateLinkIssueJson() {
        return UPDATE_LINK_ISSUE;
    }

    public static void set_UpdateLinkIssueJson(String parentIssueKey) {
        UPDATE_LINK_ISSUE = String.format(UPDATE_LINK_ISSUE, parentIssueKey);
    }

    public static String get_UpdateStatusNewJson() {
        return "{\n"
                + "  \"transition\": {\n"
                + "    \"id\": \"11\",\n"
                + "    \"name\": \"Select for Development\",\n"
                + "    \"to\": {\n"
                + "      \"id\": \"10101\",\n"
                + "      \"name\": \"New request\"\n"
                + "    }\n"
                + "  }\n"
                + "}";
    }

    public static String get_IssueCommentJson() {
        return UPDATE_ISSUE_COMMENT_NEW;
    }

    public static void set_IssueCommentJson(
            String tagUser, String issueDetail, String modeName, String executeDate) {
        issueDetail = issueDetail.replace("\n", "\\n").replace("\"", "'");
        UPDATE_ISSUE_COMMENT_NEW =
                String.format(UPDATE_ISSUE_COMMENT_NEW, tagUser, issueDetail, modeName, executeDate);
    }

    public static String get_IssueCommentImageJson() {
        return UPDATE_ISSUE_COMMENT_IMAGE;
    }

    public static void set_IssueCommentImageJson(
            String tagUser, String issueDetail, String imageName, String modeName, String executeDate) {
        issueDetail = issueDetail.replace("\n", "\\n").replace("\"", "'");
        UPDATE_ISSUE_COMMENT_IMAGE =
                String.format(
                        UPDATE_ISSUE_COMMENT_IMAGE, tagUser, issueDetail, imageName, modeName, executeDate);
    }

    public static String get_TOKEN() {
        String tokenStr = USERNAME + ":" + USERTOKEN;
        return Base64.getEncoder().encodeToString(tokenStr.getBytes());
    }

    public static String get_CreateStoryJson() {
        return CREATE_STORY;
    }

    public static void set_CreateStoryJson(
            String projKey, String summary, String description, String assignee) {
        description = description.replace("\n", "\\n").replace("\"", "'");
        CREATE_STORY = String.format(CREATE_STORY, projKey, summary, description, assignee);
    }
}
