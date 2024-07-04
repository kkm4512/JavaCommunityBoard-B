package JavaCommunityBoard;

import JavaCommunityBoard.Exceptions.*;
import JavaCommunityBoard.types.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        // 에러 메시지를 저장할 Map 생성
        Map<String,String> errors = new HashMap<>();
        HttpStatus status = HttpStatus.BAD_REQUEST;

        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            //error 발생된 이름
            String fieldName = ((FieldError) error).getField();
            //error 발생한 이름의 메시지내용
            String errorMessage = error.getDefaultMessage();

            //에러메시지에 공백이 포함되어있으면 status 바꾸기
            if (errorMessage.contains("공백")){
                status = HttpStatus.NOT_FOUND;
            }
            //errors 객체에 추가
            errors.put(fieldName,errorMessage);
        }

        ErrorResponse errorResponse = new ErrorResponse(status.value(),errors);
        return ResponseEntity.status(status).body(errorResponse);
    }

    @ExceptionHandler(HandleDuplicateMember.class)
    public ResponseEntity<ErrorResponse> handleDuplicateUserException(HandleDuplicateMember e) {
        return createErrorResponse(e.getMessage(),HttpStatus.CONFLICT);
    }

    @ExceptionHandler(HandleMisMatchUserInfo.class)
    public ResponseEntity<ErrorResponse> HandleMisMatchUserInfo(HandleMisMatchUserInfo e) {
        return createErrorResponse(e.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HandleMisMatchBoardInfo.class)
    public ResponseEntity<ErrorResponse> HandleMisMatchUserInfo(HandleMisMatchBoardInfo e) {
        return createErrorResponse(e.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HandleEmptyMemberInfo.class)
    public ResponseEntity<ErrorResponse> HandleMisMatchUserInfo(HandleEmptyMemberInfo e) {
        return createErrorResponse(e.getMessage(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<ErrorResponse> createErrorResponse (String msg, HttpStatus status) {
        Map<String,String> errors = new HashMap<>();
        errors.put("error",msg);
        ErrorResponse responseBody = new ErrorResponse(status.value(),errors);
        return ResponseEntity.status(status).body(responseBody);
    }
}
