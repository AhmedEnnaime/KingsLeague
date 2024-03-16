import { TeamModalProps } from "../propsTypes/TeamModalProps";
import { Dialog, Transition } from "@headlessui/react";
import { Fragment, useEffect, useRef, useState } from "react";
import { useAppDispatch } from "../redux/store";
import ITeam from "../interfaces/ITeam";
import { createTeam, updateTeam } from "../redux/teams/teamActions";
import { toast } from "react-toastify";
import axios from "axios";
import ICountry from "../interfaces/ICountry";

const TeamModal = ({ open, setOpen, team }: TeamModalProps) => {
  const cancelButtonRef = useRef(null);
  const dispatch = useAppDispatch();
  const [countries, setCountries] = useState<string[]>([]);

  const fetchCountries = async () => {
    try {
      const response = await axios.get<ICountry[]>(
        `https://restcountries.com/v3.1/all`
      );
      const countriesData = response.data;
      const countryNames = countriesData.map(
        (country: ICountry) => country.name.common
      );
      setCountries(countryNames);
    } catch (error) {
      console.log(error);
    }
  };

  const [inputs, setInputs] = useState<ITeam>({
    name: team?.name ?? "",
    country: team?.country ?? "",
  });
  const handleChange = (
    e:
      | React.ChangeEvent<HTMLInputElement>
      | React.ChangeEvent<HTMLSelectElement>
  ) => {
    setInputs((prevState) => ({
      ...prevState,
      [e.target.name]: e.target.value,
    }));
  };

  const handleAddSubmit = async (e: React.FormEvent<EventTarget>) => {
    e.preventDefault();
    dispatch(createTeam(inputs))
      .then(() => {
        toast.success("Team created successfully");
        setOpen(false);
      })
      .catch((err) => {
        console.error("Failed to create team:", err);
        toast.error("Failed to create team");
      });
  };

  const handleUpdateSubmit = async (e: React.FormEvent<EventTarget>) => {
    e.preventDefault();
    const { id } = team as ITeam;
    const updatedTeamData = inputs;
    const teamID = id as number;
    dispatch(updateTeam({ id: teamID, teamData: updatedTeamData }))
      .then(() => {
        toast.success("Team updated successfully");
        setOpen(false);
      })
      .catch((err) => {
        console.error("Failed to update team:", err);
        toast.error("Failed to update team");
      });
  };

  useEffect(() => {
    fetchCountries();
  }, []);
  return (
    <Transition.Root show={open} as={Fragment}>
      <Dialog
        as="div"
        className="relative z-10"
        initialFocus={cancelButtonRef}
        onClose={setOpen}
      >
        <Transition.Child
          as={Fragment}
          enter="ease-out duration-300"
          enterFrom="opacity-0"
          enterTo="opacity-100"
          leave="ease-in duration-200"
          leaveFrom="opacity-100"
          leaveTo="opacity-0"
        >
          <div className="fixed inset-0 bg-gray-500 bg-opacity-75 transition-opacity" />
        </Transition.Child>

        <div className="fixed inset-0 z-10 overflow-y-auto">
          <div className="flex min-h-full items-end justify-center p-4 text-center sm:items-center sm:p-0">
            <Transition.Child
              as={Fragment}
              enter="ease-out duration-300"
              enterFrom="opacity-0 translate-y-4 sm:translate-y-0 sm:scale-95"
              enterTo="opacity-100 translate-y-0 sm:scale-100"
              leave="ease-in duration-200"
              leaveFrom="opacity-100 translate-y-0 sm:scale-100"
              leaveTo="opacity-0 translate-y-4 sm:translate-y-0 sm:scale-95"
            >
              <Dialog.Panel className="relative transform overflow-hidden rounded-lg bg-white px-4 pt-5 pb-4 text-left shadow-xl transition-all sm:my-8 sm:w-full sm:max-w-lg sm:p-6">
                <div>
                  <div className="mx-auto flex h-12 w-12 items-center justify-center rounded-full bg-green-100">
                    <i
                      className="p-1 fa-sharp fa-solid fa-dungeon h-6 w-6 text-green-600"
                      aria-hidden="true"
                    ></i>
                  </div>
                  <div className="mt-3 text-center sm:mt-5">
                    <Dialog.Title
                      as="h3"
                      className="text-lg font-medium leading-6 text-gray-900"
                    >
                      Fill team's information
                    </Dialog.Title>

                    <form className="flex flex-col items-center mt-4">
                      <div className="text-left w-full">
                        <label
                          htmlFor="name"
                          className="block text-sm font-medium text-gray-700"
                        >
                          Name
                        </label>
                        <div className="mt-1">
                          <input
                            type="text"
                            name="name"
                            value={inputs?.name}
                            onChange={handleChange}
                            id="name"
                            className="block px-4 w-full h-8 rounded-md border-2 border-gray-400 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
                            placeholder="Enter stadium's name"
                          />
                        </div>
                      </div>

                      <div className="text-left w-full">
                        <label
                          htmlFor="country"
                          className="block text-sm font-medium text-gray-700"
                        >
                          Country
                        </label>
                        <div className="mt-1">
                          <select
                            onChange={handleChange}
                            value={inputs.country}
                            name="country"
                            id="country"
                            className="w-full rounded-md"
                          >
                            <option value="">Select a country</option>
                            {countries &&
                              countries.map((country, index) => (
                                <option value={country} key={index}>
                                  {country}
                                </option>
                              ))}
                          </select>
                        </div>
                      </div>
                    </form>
                  </div>
                </div>
                <div className="mt-5 sm:mt-6 sm:grid sm:grid-flow-row-dense sm:grid-cols-2 sm:gap-3">
                  {!team?.id ? (
                    <button
                      type="submit"
                      className="inline-flex w-full justify-center rounded-md border border-transparent bg-indigo-600 px-4 py-2 text-base font-medium text-white shadow-sm hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2 sm:col-start-2 sm:text-sm"
                      onClick={handleAddSubmit}
                    >
                      Add
                    </button>
                  ) : (
                    <button
                      type="submit"
                      className="inline-flex w-full justify-center rounded-md border border-transparent bg-indigo-600 px-4 py-2 text-base font-medium text-white shadow-sm hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2 sm:col-start-2 sm:text-sm"
                      onClick={handleUpdateSubmit}
                    >
                      Update
                    </button>
                  )}

                  <button
                    type="button"
                    className="mt-3 inline-flex w-full justify-center rounded-md border border-gray-300 bg-white px-4 py-2 text-base font-medium text-gray-700 shadow-sm hover:bg-gray-50 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2 sm:col-start-1 sm:mt-0 sm:text-sm"
                    onClick={() => setOpen(false)}
                    ref={cancelButtonRef}
                  >
                    Cancel
                  </button>
                </div>
              </Dialog.Panel>
            </Transition.Child>
          </div>
        </div>
      </Dialog>
    </Transition.Root>
  );
};

export default TeamModal;
