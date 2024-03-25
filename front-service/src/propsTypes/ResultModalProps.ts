import IMatch from "../interfaces/IMatch";

export type ResultModalProps = {
  open: boolean;
  setOpen: React.Dispatch<React.SetStateAction<boolean>>;
  match?: IMatch;
};
