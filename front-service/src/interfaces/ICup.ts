import TournamentType from "../enums/TournamentType";

interface ICup {
    id?: number;
    name: string;
    debutDate: Date;
    endDate: Date;
    location: string;
    teamsNum: number;
    tournamentType?: TournamentType;
    createdAt?: Date;
    updatedAt?: Date;
}

export default ICup;