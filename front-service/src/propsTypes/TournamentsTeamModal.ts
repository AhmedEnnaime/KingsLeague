import ITeam from "../interfaces/ITeam";

export type TournamentsTeamModalProps = {
  open: boolean;
  setOpen: React.Dispatch<React.SetStateAction<boolean>>;
  team?: ITeam;
};
