import ITournament from "../interfaces/ITournament";

export type FixtureModalProps = {
  open: boolean;
  setOpen: React.Dispatch<React.SetStateAction<boolean>>;
  tournament?: ITournament;
};
