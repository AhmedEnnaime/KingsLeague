import { Disclosure } from "@headlessui/react";
import Matchup from "./Matchup";
import { ChevronUpIcon } from "@heroicons/react/20/solid";

const MatchDay = () => {
  return (
    <div className="w-full px-4">
      <div className="mx-auto w-full rounded-2xl bg-white p-2">
        <Disclosure>
          {({ open }) => (
            <>
              <Disclosure.Button className="flex w-full justify-between rounded-lg bg-blue-100 px-4 py-8 text-left text-sm font-medium text-blue-900 hover:bg-blue-200 focus:outline-none focus-visible:ring focus-visible:ring-blue-500/75">
                <span className="text-xl font-bold">MatchDay 1</span>
                <ChevronUpIcon
                  className={`${
                    open ? "rotate-180 transform" : ""
                  } h-5 w-5 text-vlue-500`}
                />
              </Disclosure.Button>
              <Matchup />
              <Matchup />
            </>
          )}
        </Disclosure>
      </div>
    </div>
  );
};

export default MatchDay;
