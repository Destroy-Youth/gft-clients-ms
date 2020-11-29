package dev.gft.example.clients;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;

import dev.gft.example.clients.clients.DataClient;
import dev.gft.example.clients.dtos.AccountDTO;
import dev.gft.example.clients.dtos.ClientDTO;
import dev.gft.example.clients.services.ClientsService;
import feign.FeignException;

@ExtendWith(MockitoExtension.class)
public class ClientsServiceTest {

    @Mock
    private DataClient dataClient;

    @InjectMocks
    private ClientsService clientsService;

    @BeforeEach
    void init() {
        assertNotNull(dataClient);
        assertNotNull(clientsService);
    }

    @Test
    @DisplayName("Find one")
    void findOne() {

        AccountDTO account = new AccountDTO();
        account.setAccountNumber("6546466");
        account.setAccountType("basic");
        account.setBalance(BigDecimal.valueOf(21861.21));

        List<AccountDTO> accounts = new ArrayList<>();
        accounts.add(account);

        ClientDTO client = new ClientDTO();
        client.setBirthDate(new Date());
        client.setFirstName("Jhon");
        client.setLastName("Jhonson");
        client.setSex("m");
        client.setAccounts(accounts);

        when(dataClient.findOneById(anyLong())).thenReturn(ResponseEntity.ok().body(EntityModel.of(client)));

        assertNotNull(clientsService.findOneById(1L));

    }

    @Test
    @DisplayName("Find one, no results")
    void notFindOne() {
        FeignException exception = Mockito.mock(FeignException.class);
        when(dataClient.findOneById(anyLong())).thenThrow(exception);

        assertThrows(FeignException.class, () -> clientsService.findOneById(1L));
    }

    @Test
    @DisplayName("Create one")
    void createOne() {
        AccountDTO account = new AccountDTO();
        account.setAccountNumber("6546466");
        account.setAccountType("basic");
        account.setBalance(BigDecimal.valueOf(21861.21));

        List<AccountDTO> accounts = new ArrayList<>();
        accounts.add(account);

        ClientDTO client = new ClientDTO();
        client.setBirthDate(new Date());
        client.setFirstName("Jhon");
        client.setLastName("Jhonson");
        client.setSex("m");
        client.setAccounts(accounts);

        when(dataClient.createOne(any(ClientDTO.class))).thenReturn(ResponseEntity.ok().body(EntityModel.of(client)));

        assertNotNull(clientsService.createOne(client));
    }

    @Test
    @DisplayName("Update one")
    void updateOne() {
        AccountDTO account = new AccountDTO();
        account.setAccountNumber("6546466");
        account.setAccountType("basic");
        account.setBalance(BigDecimal.valueOf(21861.21));

        List<AccountDTO> accounts = new ArrayList<>();
        accounts.add(account);

        ClientDTO client = new ClientDTO();
        client.setBirthDate(new Date());
        client.setFirstName("Jhon");
        client.setLastName("Jhonson");
        client.setSex("m");
        client.setAccounts(accounts);

        when(dataClient.findOneById(anyLong())).thenReturn(ResponseEntity.ok().body(EntityModel.of(client)));
        when(dataClient.updateOne(anyLong(), any(ClientDTO.class)))
                .thenReturn(ResponseEntity.ok().body(EntityModel.of(client)));

        assertNotNull(clientsService.updateOne(1L, client));
    }

    @Test
    @DisplayName("Update one not found")
    void updateOneNotFound() {

        AccountDTO account = new AccountDTO();
        account.setAccountNumber("6546466");
        account.setAccountType("basic");
        account.setBalance(BigDecimal.valueOf(21861.21));

        List<AccountDTO> accounts = new ArrayList<>();
        accounts.add(account);

        ClientDTO client = new ClientDTO();
        client.setBirthDate(new Date());
        client.setFirstName("Jhon");
        client.setLastName("Jhonson");
        client.setSex("m");
        client.setAccounts(accounts);

        FeignException exception = Mockito.mock(FeignException.class);
        when(dataClient.findOneById(anyLong())).thenThrow(exception);

        assertThrows(FeignException.class, () -> clientsService.updateOne(1L, client));
    }

    @Test
    @DisplayName("Delete one")
    void deleteOne() {
        FeignException exception = Mockito.mock(FeignException.class);

        assertDoesNotThrow(() -> clientsService.deleteOne(1L));
    }

    @Test
    @DisplayName("Delete one not found")
    void deleteOneNotFound() {
        FeignException exception = Mockito.mock(FeignException.class);
        doThrow(exception).when(dataClient).deleteOne(anyLong());

        assertThrows(FeignException.class, () -> clientsService.deleteOne(1L));
    }

    @Test
    @DisplayName("Clients full names, no filter")
    void getClientNames() {

        ClientDTO jhon = new ClientDTO();
        jhon.setFirstName("Jhon");
        jhon.setLastName("Jhon");
        ClientDTO jhon2 = new ClientDTO();
        jhon2.setFirstName("Jhon2");
        jhon2.setLastName("Jhon2");
        ClientDTO jhon3 = new ClientDTO();
        jhon3.setFirstName("Jhon3");
        jhon3.setLastName("Jhon3");
        ClientDTO jhon4 = new ClientDTO();
        jhon4.setFirstName("Jhon4");
        jhon4.setLastName("Jhon4");

        List<ClientDTO> clients = new ArrayList<>();
        clients.add(jhon);
        clients.add(jhon4);
        clients.add(jhon2);
        clients.add(jhon3);

        when(dataClient.findAllClients()).thenReturn(ResponseEntity.ok(CollectionModel.of(clients)));

        List<String> result = assertDoesNotThrow(() -> clientsService.getAllClientFullNames(""));
        assertNotNull(result.get(0));
        assertEquals(4, result.size());
    }

    @Test
    @DisplayName("Clients full names, w/filter")
    void getClientNamesWithFilter() {

        ClientDTO jhon = new ClientDTO();
        jhon.setFirstName("Jhon");
        jhon.setLastName("Jhon");
        ClientDTO jhon2 = new ClientDTO();
        jhon2.setFirstName("Jhon2");
        jhon2.setLastName("Jhon2");
        ClientDTO jhon3 = new ClientDTO();
        jhon3.setFirstName("Jhon3");
        jhon3.setLastName("Jhon3");
        ClientDTO jhon4 = new ClientDTO();
        jhon4.setFirstName("Jhon4");
        jhon4.setLastName("Jhon4");

        List<ClientDTO> clients = new ArrayList<>();
        clients.add(jhon);
        clients.add(jhon4);
        clients.add(jhon2);
        clients.add(jhon3);

        when(dataClient.findAllClients()).thenReturn(ResponseEntity.ok(CollectionModel.of(clients)));

        List<String> result = assertDoesNotThrow(() -> clientsService.getAllClientFullNames("2"));
        assertNotNull(result.get(0));
        assertEquals(1, result.size());
    }

}
