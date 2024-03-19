import IPlayer from "../interfaces/IPlayer";

export type PlayerModalProps = {
  open: boolean;
  setOpen: React.Dispatch<React.SetStateAction<boolean>>;
  player?: IPlayer;
  setPlayer?: React.Dispatch<React.SetStateAction<IPlayer>>;
};
