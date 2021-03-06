package com.ngoc.bookmanagement.validation;

import com.ngoc.bookmanagement.model.Comment;
import com.ngoc.bookmanagement.model.MessageResponse;

public interface GroupCommentCreate {

    MessageResponse checkCommentIsExist(long commentId);

    MessageResponse validateComment(Comment comment, Class classValidate);

}
