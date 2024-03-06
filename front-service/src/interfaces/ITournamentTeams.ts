import { TournamentTeamKey } from "../embddables/TournamentTeamKey";
import ITeam from "./ITeam";
import ITournament from "./ITournament";

interface ITournamentTeams {
    id?: TournamentTeamKey;
    team: ITeam;
    tournament: ITournament;
    createdAt?: Date;
    updatedAt?: Date;
}

export default ITournamentTeams;