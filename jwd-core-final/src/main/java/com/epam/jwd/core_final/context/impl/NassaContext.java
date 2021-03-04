package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.domain.*;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.factory.impl.CrewMemberFactory;
import com.epam.jwd.core_final.factory.impl.PlanetFactory;
import com.epam.jwd.core_final.factory.impl.SpaceshipFactory;
import com.epam.jwd.core_final.util.PropertyReaderUtil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

// todo
public class NassaContext implements ApplicationContext {
    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(NassaContext.class);
    // no getters/setters for them
    private Collection<CrewMember> crewMembers = new ArrayList<>();
    private Collection<Spaceship> spaceships = new ArrayList<>();
    private Collection<Planet> planetMap = new ArrayList<>();
    private Collection<FlightMission> flightMissions = new ArrayList<>();
    private ApplicationProperties applicationProperties;

    @Override
    public <T extends BaseEntity> Collection<T> retrieveBaseEntityList(Class<T> tClass) {
        return tClass == CrewMember.class ? (Collection<T>) crewMembers : tClass == Spaceship.class ? (Collection<T>) spaceships : tClass == Planet.class ? (Collection<T>) planetMap : tClass == FlightMission.class ? (Collection<T>) flightMissions : null;
    }

    /**
     * You have to read input files, populate collections
     * @throws InvalidStateException
     */
    @Override
    public void init() throws IOException {
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
                try{
                    CrewMember crewMember = crewMemberFactory.create(Rank.resolveRankById(k), Role.resolveRoleById(i), name);
                    if (crewMember == null) throw new InvalidStateException("crew");
                    else crewMembers.add(crewMember);
                }catch (InvalidStateException e){
                    System.out.println(e.getMessage());
                }
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
                if((char) buf == '\n') buf = file.read();
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
                try{
                    Spaceship spaceship = spaceshipFactory.create(map,name,n);
                    if(spaceship == null) throw new InvalidStateException("spaceship");
                    else spaceships.add(spaceshipFactory.create(map,name,n));
                }catch (InvalidStateException e){
                    System.out.println(e.getMessage());
                }
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
                else {
                    try {
                        Planet planet = planetFactory.create(i, k, temp);
                        if (planet == null) throw new InvalidStateException("planet");
                        else planetMap.add(planetFactory.create(i, k, temp));
                    } catch (InvalidStateException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }while(file.available() != 0);
        }catch(FileNotFoundException e){
            logger.error(e.getMessage());
            e.printStackTrace();
        }
//        logger.info("Planets completely were read from the file");
    }
}
