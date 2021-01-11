

package com.ahianzhang.auth.api;



import com.ahianzhang.auth.api.vo.client.CreateClientResponse;
import com.ahianzhang.auth.domain.client.Client;
import com.ahianzhang.auth.domain.client.ClientService;
import com.ahianzhang.auth.infrastructure.exception.ErrorMessageEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * @author ahianzhang
 * @version 1.0
 * @date 2020/9/11 4:56 PM
 **/
@RestController
@RequestMapping("/clients")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }
    @PostMapping
    public ResponseEntity<CreateClientResponse> createClient(@RequestBody  Client client){
        Client clientResp = clientService.create(client);
        if (Objects.isNull(clientResp)){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new CreateClientResponse(ErrorMessageEnum.WAS_CREATED_CLIENT.getResultMessage()));
        }
        CreateClientResponse clientResponse = new CreateClientResponse(clientResp.getClientId(),clientResp.getClientSecret());
        return ResponseEntity.status(HttpStatus.CREATED).body(clientResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> findClient(@PathVariable("id") Integer id){
        Client client = clientService.findClientById(id);
        return ResponseEntity.ok(client);
    }
    @GetMapping
    public ResponseEntity<Client> findByClientName(@RequestParam("name") String clientName){
        Client client = clientService.findByClientName(clientName);
        return ResponseEntity.ok(client);
    }
}
