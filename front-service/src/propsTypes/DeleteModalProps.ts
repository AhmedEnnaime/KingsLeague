import IStadium from "../interfaces/IStadium";
import ITournament from "../interfaces/ITournament";
import ITournamentTeams from "../interfaces/ITournamentTeams";

export type DeleteModalProps = {
  open: boolean;
  setOpen: React.Dispatch<React.SetStateAction<boolean>>;
  element: IStadium | ITournament | ITournamentTeams;
};
