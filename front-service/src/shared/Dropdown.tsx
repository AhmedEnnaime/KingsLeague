import { useState } from "react";
import TournamentType from "../enums/TournamentType";
import { TournamentDropdownProps } from "../propsTypes/DropdownContent";

const Dropdown = ({ onSelect }: TournamentDropdownProps) => {
  const [isOpen, setIsOpen] = useState(false);

  const toggleDropdown = () => {
    setIsOpen(!isOpen);
  };

  const handleSelection = (tournamentType: TournamentType) => {
    onSelect(tournamentType);
    setIsOpen(false);
  };

  return (
    <div className="relative inline-block border-2 rounded-md border-black">
      <button
        onClick={toggleDropdown}
        className="relative z-10 flex items-center p-2 text-sm text-gray-600 bg-gray-100 border border-transparent rounded-md focus:border-blue-500 focus:ring-opacity-40 focus:ring-blue-300 focus:outline-none"
      >
        <span className="mx-1">Tournament Type</span>
        <svg
          className="w-5 h-5 mx-1"
          viewBox="0 0 24 24"
          fill="none"
          xmlns="http://www.w3.org/2000/svg"
        >
          <path
            d="M12 15.713L18.01 9.70299L16.597 8.28799L12 12.888L7.40399 8.28799L5.98999 9.70199L12 15.713Z"
            fill="currentColor"
          ></path>
        </svg>
      </button>
      {isOpen && (
        <div
          onClick={() => setIsOpen(false)}
          className="absolute right-0 z-20 w-56 py-2 mt-2 overflow-hidden origin-top-right bg-white rounded-md shadow-xl"
        >
          <span
            onClick={() => handleSelection(TournamentType.LEAGUE)}
            className="block cursor-pointer px-4 py-3 text-sm text-gray-600 capitalize transition-colors duration-300 transform dark:text-gray-300 hover:bg-gray-100 dark:hover:bg-gray-700 dark:hover:text-white"
          >
            {TournamentType.LEAGUE}
          </span>
          <span
            onClick={() => handleSelection(TournamentType.CUP)}
            className="block cursor-pointer px-4 py-3 text-sm text-gray-600 capitalize transition-colors duration-300 transform dark:text-gray-300 hover:bg-gray-100 dark:hover:bg-gray-700 dark:hover:text-white"
          >
            {TournamentType.CUP}
          </span>
        </div>
      )}
    </div>
  );
};

export default Dropdown;
