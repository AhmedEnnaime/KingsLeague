import ITeam from "../interfaces/ITeam";

export type TeamModalProps = {
  open: boolean;
  setOpen: React.Dispatch<React.SetStateAction<boolean>>;
  team?: ITeam;
  setTeam?: React.Dispatch<React.SetStateAction<ITeam>>;
};
