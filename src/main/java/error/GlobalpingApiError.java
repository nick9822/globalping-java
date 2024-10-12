package error;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;
import model.enums.CustomGson;

public class GlobalpingApiError extends Exception {

  private final int statusCode;

  public GlobalpingApiError(int statusCode, String message) {
    super(message);
    this.statusCode = statusCode;
  }

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
