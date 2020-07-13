package spring.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import spring.entity.BadAddress;
import spring.entity.GoodAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.service.BadAddressService;
import spring.service.GoodAddressService;

@RestController
@RequestMapping("/response")
public class AddressController {
    @Autowired
    private BadAddressService badAddressService;
    @Autowired
    private GoodAddressService goodAddressService;

    @PostMapping(value = "/address")
    public ResponseEntity<GoodAddress> responseAddress(@RequestBody String badAddressName) {
        BadAddress badAddress = badAddressService.findByName(badAddressName);
        if (badAddress == null) {
            JSONObject fullAddress = DadataUtils.getStandartAddress(badAddressName);

            if (fullAddress.get("country") == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            badAddress = new BadAddress();
            badAddress.setBadAddress(badAddressName);
            String goodAddressName = fullAddress.get("country") + ", " + fullAddress.get("result") + ", " + fullAddress.get("postal_code");
            GoodAddress goodAddress = goodAddressService.findByName(goodAddressName);
            if (goodAddress == null) {
                goodAddress = goodAddressService.addAddress(new GoodAddress(goodAddressName));
            }
            badAddress.setGoodAddress(goodAddress);

            badAddressService.addAddress(badAddress);
            return new ResponseEntity<>(goodAddress, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(badAddress.getGoodAddress(), HttpStatus.OK);
        }
    }
}
