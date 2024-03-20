import { Disclosure } from "@headlessui/react";
import logo from "../assets/team_logo.png";
import { MatchupProps } from "../propsTypes/MatchupProps";

const Matchup = ({ matches }: MatchupProps) => {
  return (
    <Disclosure.Panel className="px-4 pb-2 pt-4 text-sm text-gray-500">
      {matches &&
        matches.map((match) => (
          <div
            key={match.id}
            className="flex justify-between items-center gap-4 p-4"
          >
            <div className="flex items-center gap-4 p-2">
              <img
                alt="Bonnie image"
                height="96"
                src={logo}
                width="96"
                className="mb-3 rounded-full shadow-lg"
              />
              <p className="text-xl font-bold">{match.teamA?.name}</p>
            </div>

            <span className="text-xl font-bold">4 - 0</span>

            <div className="flex items-center gap-4 p-2">
              <img
                alt="Bonnie image"
                height="96"
                src={logo}
                width="96"
                className="mb-3 rounded-full shadow-lg"
              />
              <p className="text-xl font-bold">{match.teamB?.name}</p>
            </div>
          </div>
        ))}
    </Disclosure.Panel>
  );
};

export default Matchup;
