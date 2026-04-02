package com.cms.dto;

<<<<<<< HEAD
public class ApiResponse<T> {

    private boolean success;
    private String  message;
    private T       data;

    // ── No-args constructor ──────────────────────────────────────────────────
    public ApiResponse() {}

    // ── All-args constructor ─────────────────────────────────────────────────
    public ApiResponse(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data    = data;
    }

    // ── Static factory helpers ───────────────────────────────────────────────
=======
/**
 * Generic API Response class to standardize REST API responses.
 *
 * @param <T> the type of the data payload
 */
public class ApiResponse<T> {

    private boolean success;
    private String message;
    private T data;

    // No-args constructor
    public ApiResponse() {}

    // All-args constructor
    public ApiResponse(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    // Getters and Setters
    public boolean isSuccess() {
        return success;
    }
    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }

    // Static helper methods
>>>>>>> 06559c1 (Initial commit for Render deployment)
    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(true, message, data);
    }

    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(false, message, null);
    }
<<<<<<< HEAD

    // ── Getters ──────────────────────────────────────────────────────────────
    public boolean isSuccess() { return success; }
    public String  getMessage(){ return message; }
    public T       getData()   { return data; }

    // ── Setters ──────────────────────────────────────────────────────────────
    public void setSuccess(boolean success) { this.success = success; }
    public void setMessage(String message)  { this.message = message; }
    public void setData(T data)             { this.data = data; }
}
=======
}
>>>>>>> 06559c1 (Initial commit for Render deployment)
