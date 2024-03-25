import IMatchDay from "../interfaces/IMatchDay";
import IRound from "../interfaces/IRound";

export type MatchModalProps = {
  open: boolean;
  setOpen: React.Dispatch<React.SetStateAction<boolean>>;
  fixture?: IMatchDay | IRound;
  setFixture?: React.Dispatch<React.SetStateAction<IMatchDay | IRound>>;
};
