package com.blog.exception;

public class CustomException {
    public static DataSourceException DATA_SOURCE_NOT_WORKING = new DataSourceException("API 호출에 실패하였습니다.");
    public static NotSupportProviderException NOT_SUPPORT_PROVIDER = new NotSupportProviderException("Cannot found the Provider.");
}
