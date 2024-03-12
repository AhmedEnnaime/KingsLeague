import { TournamentCardProps } from "../propsTypes/TournamentCradProps";
import Button from "../shared/Button";

const TournamentCard = ({ tournament }: TournamentCardProps) => {
  return (
    <div className="w-full px-4 py-3 bg-white rounded-md shadow-md dark:bg-gray-800">
      <div className="flex items-center justify-between bg-gray-300 w-full">
        <span className="mt-2 text-2xl font-bold text-gray-800 dark:text-white">
          {tournament.name}
        </span>
        <span className="px-3 py-1 text-xs text-blue-800 uppercase bg-blue-200 rounded-full dark:bg-blue-300 dark:text-blue-900">
          {tournament.tournamentType}
        </span>
      </div>

      <div className="flex justify-between p-2">
        <h1 className="mt-2 text-lg font-medium text-gray-800 dark:text-white">
          Debut date
        </h1>
        <p className="mt-2 text-sm text-gray-600 dark:text-gray-300">
          {tournament.debutDate}
        </p>
      </div>
      <div className="flex justify-between p-2">
        <h1 className="mt-2 text-lg font-medium text-gray-800 dark:text-white">
          End date
        </h1>
        <p className="mt-2 text-sm text-gray-600 dark:text-gray-300">
          {tournament.endDate}
        </p>
      </div>
      <div className="flex justify-between p-2">
        <h1 className="mt-2 text-lg font-medium text-gray-800 dark:text-white">
          Registered teams
        </h1>
        <p className="mt-2 text-sm text-gray-600 dark:text-gray-300">
          {tournament.teamsNum}
        </p>
      </div>

      <div className="flex justify-between p-2">
        <h1 className="mt-2 text-lg font-medium text-gray-800 dark:text-white">
          Location
        </h1>
        <p className="mt-2 text-sm text-gray-600 dark:text-gray-300">
          {tournament.location}
        </p>
      </div>

      <div>
        <div className="flex justify-between items-center mt-2">
          <Button content="Edit" />
          <Button bgColor="bg-red-500" content="Delete" />
        </div>
      </div>
    </div>
  );
};

export default TournamentCard;
