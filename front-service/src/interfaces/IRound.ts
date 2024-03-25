import ICup from "./ICup";
import IMatch from "./IMatch";

interface IRound {
  id?: number;
  date: string;
  cup?: ICup;
  tournamentId?: number;
  matches: IMatch[];
  createdAt?: Date;
  updatedAt?: Date;
}

export default IRound;
