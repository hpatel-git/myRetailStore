/**
 * @ copyright 2016, myRetail Corporation.
 */
package com.myretail.store.api.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * The Class ApiResponseBody.
 *
 * @author Hardikkumar patel(hardikkumar.ce@gmail.com)
 * @version 1.0
 * @since 10/15/2016
 * @param <T>  the generic type
 * 
 */

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ApiResponseBody<T>  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private boolean success;
	@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
	private transient List<ErrorDetail> errors = new ArrayList<>();
	@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
	private transient T data;

	/**
	 * Instantiates a new api response body.
	 *
	 * @param builder
	 *            the builder
	 */
	public ApiResponseBody(ResponseBuilder<T> builder) {
		this.success = builder.success;
		this.data = builder.data;
		this.errors = builder.errors;
	}

	/**
	 * Checks if is success.
	 *
	 * @return true, if is success
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * Gets the errors.
	 *
	 * @return the errors
	 */
	public List<ErrorDetail> getErrors() {
		return errors;
	}

	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public T getData() {
		return data;
	}

	/**
	 * The Class ResponseBuilder is build factory to create instance of APIResponseBody.
	 *
	 * @param <T>
	 *            the generic type
	 */
	public static class ResponseBuilder<T> {

		private T data;
		private List<ErrorDetail> errors;
		private boolean success;

		/**
		 * Instantiates a new response builder.
		 *
		 * @param errorMessage
		 *            the error message
		 */
		public ResponseBuilder(String errorMessage) {
			errors = new ArrayList<>();
			this.success = false;
			ErrorDetail errorDetail = new ErrorDetail(errorMessage);
			errors.add(errorDetail);
		}

		/**
		 * Instantiates a new response builder.
		 */
		public ResponseBuilder() {
			errors = null;
			data = null;
		}

		/**
		 * Instantiates a new response builder.
		 *
		 * @param data
		 *            the data
		 */
		public ResponseBuilder(T data) {
			this.data = data;
			this.success = true;
		}

		/**
		 * Adds the more error.
		 *
		 * @param errorMessage
		 *            the error message
		 * @return the response builder
		 */
		public ResponseBuilder<T> addMoreError(String errorMessage) {
			this.success = false;
			if (!Optional.ofNullable(errors).isPresent()) {
				errors = new ArrayList<>();
			}
			ErrorDetail errorDetail = new ErrorDetail(errorMessage);
			errors.add(errorDetail);
			return this;
		}

		/**
		 * Adds the more error.
		 *
		 * @param errorDetail
		 *            the error detail
		 * @return the response builder
		 */
		public ResponseBuilder<T> addMoreError(ErrorDetail errorDetail) {
			this.success = false;
			if (!Optional.ofNullable(errors).isPresent()) {
				errors = new ArrayList<>();
			}
			errors.add(errorDetail);
			return this;
		}

		/**
		 * Builds the.
		 *
		 * @return the api response body
		 */
		public ApiResponseBody<T> build() {
			return new ApiResponseBody<>(this);
		}
	}
}
