import TournamentType from "../enums/TournamentType";

export type TournamentDropdownProps = {
  onSelect: (tournamentType: TournamentType) => void;
};
