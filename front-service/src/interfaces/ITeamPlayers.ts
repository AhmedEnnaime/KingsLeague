import { TeamPlayerKey } from "../embddables/TeamPlayerKey";
import IPlayer from "./IPlayer";
import ITeam from "./ITeam";

interface ITeamPlayers {
    id?: TeamPlayerKey;
    player: IPlayer;
    team: ITeam;
    joinedAt?: Date;
    updatedAt?: Date;
}

export default ITeamPlayers;