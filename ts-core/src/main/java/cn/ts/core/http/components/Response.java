package cn.ts.core.http.components;

public class Response {
    private int statusCode;
    private String responseAsString;

    public Response() {
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getResponseAsString() {
        return responseAsString;
    }

    public void setResponseAsString(String responseAsString) {
        this.responseAsString = responseAsString;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public String toString() {
        return "Response [statusCode=" + statusCode + ", responseAsString=" + responseAsString + "]";
    }
}
