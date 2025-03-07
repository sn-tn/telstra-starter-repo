package au.com.telstra.simcardactivator;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
public class SimCardController {
  @PostMapping(path = "/activate")
  public void activateSuccess(@RequestBody ActivationRequest activationReq) {
    // stores url of SimCardActivator endpoint as string
    String url = "http://localhost:8444/actuate";

    // creates request JSON as a Map that contains ICCID
    Map<String, Object> request = new HashMap<>();
    request.put("iccid", activationReq.getIccid());
    // use RestTemplate to make request to SimCardActivator and return response as a Map
    RestTemplate restTemplate = new RestTemplate();
    Map<String, Object> response = restTemplate.postForObject(url, request, Map.class);

    // print value of "success" in response
    System.out.println(response.get("success"));
  }
}
