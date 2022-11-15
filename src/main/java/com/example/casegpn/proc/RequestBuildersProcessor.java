package com.example.casegpn.proc;


import com.example.casegpn.dto.VkRequestDTO;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import javax.validation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class RequestBuildersProcessor implements Processor {


    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();

    String GROUP_REQUEST = "https://api.vk.com/method/groups.isMember?bridgeEndpoint=true";
    String NAME_REQUEST = "https://api.vk.com/method/users.get?bridgeEndpoint=true";
    String TOKEN_HEADER = "vk_service_token";

    @Override
    public void process(Exchange exchange) throws Exception {
        Message message = exchange.getIn();
        List<String> requests = new ArrayList<>();
        VkRequestDTO vkParams = message.getBody(VkRequestDTO.class);


        Set<ConstraintViolation<VkRequestDTO>> constraints =  validator.validate(vkParams);
        if (!constraints.isEmpty()){
            throw new ConstraintViolationException(constraints);
        }

        requests.add(
                String.format(
                        GROUP_REQUEST +"&user_id=%s&group_id=%s&access_token=%s&v=5.131",
                        vkParams.getUserId(),
                        vkParams.getGroupId(),
                        exchange.getIn().getHeader(TOKEN_HEADER)
                )
        );

        requests.add(
                String.format(
                        NAME_REQUEST +"&user_id=%s&access_token=%s&fields=nickname&v=5.131",
                        vkParams.getUserId(),
                        exchange.getIn().getHeader(TOKEN_HEADER)
                )
        );


        exchange.getIn().setBody(requests);
    }
}
