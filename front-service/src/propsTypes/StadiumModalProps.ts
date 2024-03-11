import IStadium from "../interfaces/IStadium";

export type StadiumModalProps = {
  open: boolean;
  setOpen: React.Dispatch<React.SetStateAction<boolean>>;
  stadium?: IStadium;
  setStadium?: React.Dispatch<React.SetStateAction<IStadium>>;
};
