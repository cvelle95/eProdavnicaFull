package com.eprodavnicaspring.eprodavnicaSpring.controller;

import com.eprodavnicaspring.eprodavnicaSpring.misc.UsernameAlreadyExistsException;
import com.eprodavnicaspring.eprodavnicaSpring.model.KorisnikModel;
import com.eprodavnicaspring.eprodavnicaSpring.model.KorisnikReturnModel;
import com.eprodavnicaspring.eprodavnicaSpring.model.OmiljeneKategorijeProizvodaModel;
import com.eprodavnicaspring.eprodavnicaSpring.service.KorisnikServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("korisnik")
public class Korisnik {
    @Autowired
    private KorisnikServiceImpl korisnikService;

    @GetMapping("getkorisnikbyid/{id}")
    @CrossOrigin(origins = "*") //Pronalazi korisnika sa username i inner join-uje omiljene kategorije korisnika
    public KorisnikModel getKorisnikById(@PathVariable int id) {
        return korisnikService.korisnikRepository.getById(id);
    }

    @GetMapping("findbyusernamesql")
    @CrossOrigin(origins = "*") //Pronalazi korisnika sa username i inner join-uje omiljene kategorije korisnika
    public KorisnikModel findByUsernameSql(String username) {
        return korisnikService.findByUsernameSql(username);
    }

    @GetMapping("isusernameregistered")
    @CrossOrigin(origins = "*") //Proverava da li vec postoji registrovan korisnik sa datim username,ako ima vraca true
    public boolean isUsernameRegistered(String username) {
        return korisnikService.isUsernameRegistered(username);
    }

    @PostMapping("insertnewkorisnik")
    @CrossOrigin(origins = "*")
    public KorisnikModel insertNewKorisnik(@RequestBody KorisnikModel korisnikModel) throws UsernameAlreadyExistsException {
        String username = korisnikModel.getUsername();
        if (korisnikService.isUsernameRegistered(username)) {
            throw new UsernameAlreadyExistsException(username);
        }
        return korisnikService.insertNewKorisnik(korisnikModel);
    }

    @PostMapping("updatekorisnik")
    @CrossOrigin(origins = "*")
    public KorisnikModel updateKorisnik(@RequestBody KorisnikModel korisnikModel) {
        return korisnikService.updateKorisnik(korisnikModel);
    }

    @PostMapping("addomiljenukategorijuproizvoda")
    @CrossOrigin(origins = "*")
    public OmiljeneKategorijeProizvodaModel addOmiljenuKategorijuProizvoda(@RequestBody OmiljeneKategorijeProizvodaModel omiljeneKategorijeProizvodaModel) {
        return korisnikService.addOmiljenuKategorijuProizvoda(omiljeneKategorijeProizvodaModel);
    }

    @PostMapping("loginuser")
    @CrossOrigin(origins = "*")
    public KorisnikReturnModel loginUser(@RequestBody Map<String,Object> body) {
        String username = body.get("username").toString();
        String password = body.get("password").toString();
        System.out.println(username);
        System.out.println(password);
        try {
            KorisnikModel user = korisnikService.findByUsernameSql(username);
            KorisnikReturnModel userToReturn = new KorisnikReturnModel(
                    user.getId(),user.getIme(),user.getPrezime(),user.getEmail(),user.getTelefon(),user.getAdresa(),
                    user.getUsername(),user.getPassword()
            );
            System.out.println(user);
            if (user.getPassword().equals(password)) {
                System.out.println(user);
                return userToReturn;
            } else {
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
}
