package error;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;
import model.enums.CustomGson;

/**
 * Represents an API error encountered when interacting with the Globalping service. By default, it
 * throws like {@link Exception} but captures statusCode as well.
 *
 * <p>The only public constructor uses {@link #raise(ByteArrayOutputStream, HttpURLConnection)}
 * method reads the response stream, extracts the status code, and throws a specific exception based
 * on the HTTP response code as follows:</p>
 * <ul>
 * <li>{@link IOException} if an I/O error occurs while reading the response.</li>
 * <li>{@link ValidationException} if the API returns a 400 (Bad Request with Validation errors).
 * </li>
 * <li>{@link UnauthorizedException} if the API returns a 401 (Unauthorized).</li>
 * <li>{@link ForbiddenException} if the API returns a 403 (Forbidden).</li>
 * <li>{@link NotFoundException} if the API returns a 404 (Not Found).</li>
 * <li>{@link ProbesNotFoundException} if the API returns a 422 (Unprocessable Entity).</li>
 * <li>{@link TooManyRequestsException} if the API returns a 429 (Too Many Requests).</li>
 * <li>{@link InternalServerErrorException} if the API returns a 500 (Internal Server Error).</li>
 * <li>{@link GlobalpingApiError} if the API returns any other status code (default).</li>
 * </ul>
 */
public class GlobalpingApiError extends Exception {

  private int statusCode = 500;

  private GlobalpingApiError() {
  }

  GlobalpingApiError(int statusCode, String message) {
    super(message);
    this.statusCode = statusCode;
  }

  public GlobalpingApiError(ByteArrayOutputStream responseStream, HttpURLConnection conn)
      throws IOException, GlobalpingApiError {
    raise(responseStream, conn);
  }

  /**
   * This public method is used to trigger an exception depending on the Gloobalping service
   * response.
   *
   * @param responseStream {@link ByteArrayOutputStream} response from the service.
   * @param conn           {@link HttpURLConnection} underlying HTTP connection.
   * @throws IOException        gets triggered when there is an error in underlying I/O.
   * @throws GlobalpingApiError excepted and gets triggered depending on the connection response
   *                            code.
   */
  public static void raise(ByteArrayOutputStream responseStream, HttpURLConnection conn)
      throws IOException, GlobalpingApiError {
    int responseCode = conn.getResponseCode();
    switch (conn.getResponseCode()) {
      case 400:
        throw CustomGson.get().fromJson(responseStream.toString(
            String.valueOf(StandardCharsets.UTF_8)), ValidationException.class);
      case 401:
        throw new UnauthorizedException(responseStream.toString(
            String.valueOf(StandardCharsets.UTF_8)));
      case 403:
        throw new ForbiddenException(responseStream.toString(
            String.valueOf(StandardCharsets.UTF_8)));
      case 404:
        throw new NotFoundException(responseStream.toString(
            String.valueOf(StandardCharsets.UTF_8)));
      case 422:
        throw CustomGson.get().fromJson(responseStream.toString(
            String.valueOf(StandardCharsets.UTF_8)), ProbesNotFoundException.class);
      case 429:
        TooManyRequestsException tmre = CustomGson.get().fromJson(responseStream.toString(
            String.valueOf(StandardCharsets.UTF_8)), TooManyRequestsException.class);
        tmre.setHeaders(conn.getHeaderFields());
        throw tmre;
      case 500:
        throw new InternalServerErrorException("something went wrong!");
      default:
        throw new GlobalpingApiError(responseCode, "something went wrong!");
    }
  }

  public int getStatusCode() {
    return statusCode;
  }
}
