package service;

import domain.Entities.Client;
import domain.Validators.ValidatorException;
import repository.repos.DBClientRepository;
import repository.repos.Repository;
import repository.repos.SortingRepository;
import repository.impl.Sort;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ClientService implements IClientService{
    private Repository<Long, Client> clientRepository;

    public ClientService(Repository<Long, Client> new_clientRepository){
        this.clientRepository=new_clientRepository;
    }

    public void addClient(Client client) throws ValidatorException, ClassNotFoundException, ParserConfigurationException, TransformerException, SQLException, IOException {
        //adds a client
        clientRepository.save(client);
    }

    public Set<Client> getAllClients() throws IOException, ClassNotFoundException, SQLException {
        //returns a set of all clients
        Iterable<Client> clients = clientRepository.findAll();
        return StreamSupport.stream(clients.spliterator(), false).collect(Collectors.toSet());
    }

    public Client filterClientsBySerial(String s) throws IOException, ClassNotFoundException, SQLException {
        //pre:receives a string
        //post:deletes all clients that don't have the given serial number
        Iterable<Client> clients = clientRepository.findAll();
        Set<Client> filteredClients= new HashSet<>();
        clients.forEach(filteredClients::add);
        filteredClients.removeIf(client -> !client.getSerial().equals(s));

        return filteredClients.iterator().next();
    }

    public Optional<Client> findOne(long id) throws IOException, ClassNotFoundException, SQLException {
        return this.clientRepository.findOne(id);
    }

    public Set<Client> filterClientsByName(String s) throws IOException, ClassNotFoundException, SQLException {
        Iterable<Client> clients = clientRepository.findAll();
        Set<Client> filteredClients = new HashSet<>();
        clients.forEach(filteredClients::add);
        filteredClients.removeIf(client -> !client.getName().contains(s));
        return filteredClients;
    }

    public void deleteClient(long id) throws ValidatorException, IOException, ClassNotFoundException, TransformerException, ParserConfigurationException, SQLException {
        //deletes a client
        clientRepository.delete(id);
    }
    public void updateClient(Client client) throws ValidatorException, IOException, ClassNotFoundException, TransformerException, ParserConfigurationException, SQLException {
        //updates a client
        clientRepository.update(client);
    }

    public List<Client> getAllClientsSorted() throws Exception {
        //returns a set of all movies
        Sort sort = new Sort(false, "name");

        if (clientRepository instanceof SortingRepository) {
            SortingRepository<Long, Client> clientRepository1 = (DBClientRepository) clientRepository;
            Iterable<Client> clients = clientRepository1.findAll(sort);
            return StreamSupport.stream(clients.spliterator(), false).collect(Collectors.toList());
        } else {
            throw new Exception("Invalid repo!");
        }
    }
}