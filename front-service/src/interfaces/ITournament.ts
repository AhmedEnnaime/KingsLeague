import TournamentType from "../enums/TournamentType";

interface ITournament {
  id?: number;
  name: string;
  debutDate: string;
  endDate: string;
  location: string;
  teamsNum: number;
  tournamentType?: TournamentType;
  createdAt?: Date;
  updatedAt?: Date;
}

export default ITournament;
