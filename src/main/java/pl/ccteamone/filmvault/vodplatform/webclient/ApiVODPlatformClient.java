package pl.ccteamone.filmvault.vodplatform.webclient;

import org.springframework.web.client.RestTemplate;

public class ApiVODPlatformClient {
    private RestTemplate restTemplate = new RestTemplate();

    private static final String API_URL = "";



    private <T> T callGetMethod(String TYPE_URL, String url, Class<T> responseType, Object... objects) {
        return restTemplate.getForObject(TYPE_URL + url,responseType,objects);
    }


}
