import IPlayer from "../interfaces/IPlayer";
import IStadium from "../interfaces/IStadium";
import ITeam from "../interfaces/ITeam";
import ITeamPlayers from "../interfaces/ITeamPlayers";
import ITournament from "../interfaces/ITournament";
import ITournamentTeams from "../interfaces/ITournamentTeams";

export type DeleteModalProps = {
  open: boolean;
  setOpen: React.Dispatch<React.SetStateAction<boolean>>;
  element:
    | IStadium
    | ITournament
    | ITournamentTeams
    | ITeam
    | ITeamPlayers
    | IPlayer;
};
