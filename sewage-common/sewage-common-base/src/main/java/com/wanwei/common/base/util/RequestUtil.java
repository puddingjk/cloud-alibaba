package com.wanwei.common.base.util;

import com.wanwei.common.base.constants.GlobalConstant;
import com.wanwei.common.base.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.xmlbeans.impl.util.Base64;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


/**
 * The class Request util.
 *
 *
 */
@Slf4j
public class RequestUtil {

	/**
	 * Gets request.
	 *
	 * @return the request
	 */
	public static HttpServletRequest getRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}

	/**
	 * Gets auth header.
	 *
	 * @param request the request
	 *
	 * @return the auth header
	 */
	public static String getAuthHeader(HttpServletRequest request) {

		String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		if (StringUtils.isEmpty(authHeader)) {
			throw new BaseException("Authorization 为空",500);
        }
		return authHeader;
	}

	public static String[] extractAndDecodeHeader(String header) throws IOException {

		byte[] base64Token = header.substring(6).getBytes("UTF-8");
		byte[] decoded = new byte[0];
		try {
			decoded = Base64.decode(base64Token);
		} catch (IllegalArgumentException e) {
//			throw new BadCredentialsException("Failed to decode basic authentication token");
		}

		String token = new String(decoded, "UTF-8");

		int delim = token.indexOf(GlobalConstant.MH);

		if (delim == -1) {
//			throw new BadCredentialsException("Invalid basic authentication token");
		}
		return new String[]{token.substring(0, delim), token.substring(delim + 1)};
	}
}
