package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.domain.*;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.factory.impl.CrewMemberFactory;
import com.epam.jwd.core_final.factory.impl.PlanetFactory;
import com.epam.jwd.core_final.factory.impl.SpaceshipFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

// todo
public class NassaContext implements ApplicationContext {

    // no getters/setters for them
    private Collection<CrewMember> crewMembers = new ArrayList<>();
    private Collection<Spaceship> spaceships = new ArrayList<>();
    private Collection<Planet> planetMap = new ArrayList<>();

    @Override
    public <T extends BaseEntity> Collection<T> retrieveBaseEntityList(Class<T> tClass) {
        return tClass == CrewMember.class ? (Collection<T>) crewMembers : tClass == Spaceship.class ? (Collection<T>) spaceships : tClass == Planet.class ? (Collection<T>) planetMap : null;
    }

    /**
     * You have to read input files, populate collections
     * @throws InvalidStateException
     */
    @Override
    public void init() throws IOException {
        Logger logger = LoggerFactory.getLogger("InitNassaContext");
        CrewMemberFactory crewMemberFactory = new CrewMemberFactory();
        int i,k,buf;
        String name;
        Map<Role,Short> map ;
        try(FileInputStream file = new FileInputStream("/Users/andrew_wannasesh/Folders/EpamJAva/jwd-core-final/src/main/resources/input/crew")){
            do{
                name = "";
                buf = Character.getNumericValue((char) file.read());
                i = buf;
                file.skip(1);
                buf = file.read();
                do {
                    name += (char) buf;
                    buf = file.read();
                } while (buf !=',');
                buf = Character.getNumericValue((char) file.read());
                k = buf;
                file.skip(1);
                crewMembers.add(crewMemberFactory.create(Rank.resolveRankById(k), Role.resolveRoleById(i), name));
            }while(file.available() != 0);
        } catch(IOException e ){
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        logger.info("Crew members completely were read from the file");

        SpaceshipFactory spaceshipFactory = new SpaceshipFactory();
        try(FileInputStream file = new FileInputStream("/Users/andrew_wannasesh/Folders/EpamJAva/jwd-core-final/src/main/resources/input/spaceships")){
            do{
                map = new HashMap<>();
                long n;
                name ="";
                buf = file.read();
                do{
                    name += (char) buf;
                    buf = file.read();
                }while((char) buf != ';');
                String temp = "";
                buf = file.read();
                do{
                    temp += (char) buf;
                    buf = file.read();
                }while((char) buf != ';');
                n = Long.parseLong(temp);
                file.skip(1);
                buf = file.read();
                do{
                    temp = "";
                    i = Character.getNumericValue((char) buf);
                    file.skip(1);
                    buf = file.read();
                    do {
                        temp+=(char) buf;
                        buf = file.read();
                    }while(Character.isDigit((char) buf));
                    map.put(Role.resolveRoleById(i),(short) Integer.parseInt(temp));
                    if((char) buf != '}') buf = file.read();
                }while((char) buf != '}');
                spaceships.add(spaceshipFactory.create(map,name,n));
            }while(file.available() != 0);
        }catch (FileNotFoundException e){
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        logger.info("Spaceships completely were read from the file");

        PlanetFactory planetFactory = new PlanetFactory();
        try(FileInputStream file = new FileInputStream("/Users/andrew_wannasesh/Folders/EpamJAva/jwd-core-final/src/main/resources/input/spacemap")){
            i = 1;
            k = 1;
            do{
                String temp = "";
                if ((buf = file.read()) == '\n') {
                    buf = file.read();
                    ++k;
                    i = 1;
                }
                do {
                    temp += (char) buf;
                    buf = file.read();
                } while ((char) buf != ',' && buf != '\n');
                if (temp.equalsIgnoreCase("null")) ++i;
                else planetMap.add(planetFactory.create(i, k, temp));
            }while(file.available() != 0);
        }catch(FileNotFoundException e){
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        logger.info("Planets completely were read from the file");


    }
}
