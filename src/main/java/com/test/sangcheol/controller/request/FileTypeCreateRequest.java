package com.test.sangcheol.controller.request;

import com.test.sangcheol.domain.FileTypeType;
import lombok.Data;

@Data
public class FileTypeCreateRequest {

    private String fileType;
    private FileTypeType type;
    private boolean isDeleted;

}
