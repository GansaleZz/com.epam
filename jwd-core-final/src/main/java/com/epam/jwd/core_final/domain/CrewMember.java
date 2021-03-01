package com.epam.jwd.core_final.domain;

/**
 * Expected fields:
 * <p>
 * role {@link Role} - member role
 * rank {@link Rank} - member rank
 * isReadyForNextMissions {@link Boolean} - true by default. Set to false, after first failed mission
 */

//OK

public class CrewMember extends AbstractBaseEntity {
    private Role role;
    private Rank rank;
    private boolean isReadyForNextMissions;

    public CrewMember(String name,int id){
        super(name,id);
    }

    public Rank getRank() {
        return rank;
    }

    public Role getRole() {
        return role;
    }

    public boolean isReadyForNexMissions() {
        return isReadyForNextMissions;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public void setReadyForNexMissions(boolean readyForNexMissions) {
        isReadyForNextMissions = readyForNexMissions;
    }

    @Override
    public String toString() {
        return "CrewMember{" +
                "role=" + role +
                ", rank=" + rank +
                ", isReadyForNexMissions=" + isReadyForNextMissions +
                '}';
    }
}
