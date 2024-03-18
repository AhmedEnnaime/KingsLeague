import IPlayer from "../interfaces/IPlayer";

export type AddPlayerModalProps = {
  open: boolean;
  setOpen: React.Dispatch<React.SetStateAction<boolean>>;
  player?: IPlayer;
  setPlayer?: React.Dispatch<React.SetStateAction<IPlayer>>;
};
