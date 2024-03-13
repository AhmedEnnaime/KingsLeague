import ITournament from "../interfaces/ITournament";

export type TournamentModalProps = {
  open: boolean;
  setOpen: React.Dispatch<React.SetStateAction<boolean>>;
  tournament?: ITournament;
  setTournament?: React.Dispatch<React.SetStateAction<ITournament>>;
};
