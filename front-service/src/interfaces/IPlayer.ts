interface IPlayer {
    id?: number;
    firstName: string;
    lastName: string;
    weight: number;
    height: number;
    birthday: Date;
    nationality: string;
    createdAt?: Date;
    updatedAt?: Date;
}

export default IPlayer;