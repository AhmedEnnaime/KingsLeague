import { Fragment, useEffect, useRef, useState } from "react";
import { useAppDispatch } from "../redux/store";
import { PlayerModalProps } from "../propsTypes/PlayerModalProps";
import IPlayer from "../interfaces/IPlayer";
import { Dialog, Transition } from "@headlessui/react";
import axios from "axios";
import ICountry from "../interfaces/ICountry";
import { createPlayer, updatePlayer } from "../redux/players/playerActions";
import { toast } from "react-toastify";

const PlayerModal = ({ open, setOpen, player }: PlayerModalProps) => {
  const cancelButtonRef = useRef(null);
  const [countries, setCountries] = useState<string[]>([]);
  const dispatch = useAppDispatch();
  const [inputs, setInputs] = useState<IPlayer>({
    firstName: player?.firstName ?? "",
    lastName: player?.lastName ?? "",
    nationality: player?.nationality ?? "",
    weight: player?.weight ?? 0,
    height: player?.height ?? 0,
    birthday: player?.birthday ?? "",
  });

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
    dispatch(createPlayer(inputs))
      .then(() => {
        toast.success("Player created successfully");
        setOpen(false);
      })
      .catch((err) => {
        console.error("Failed to create player:", err);
        toast.error("Failed to create player");
      });
  };

  const handleUpdateSubmit = async (e: React.FormEvent<EventTarget>) => {
    e.preventDefault();
    const { id } = player as IPlayer;
    const updatedPlayerData = inputs;
    const playerID = id as number;
    dispatch(updatePlayer({ id: playerID, playerData: updatedPlayerData }))
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
                      Fill Player Information
                    </Dialog.Title>

                    <form className="flex flex-col items-center mt-4">
                      <div className="text-left w-full">
                        <label
                          htmlFor="firstName"
                          className="block text-sm font-medium text-gray-700"
                        >
                          firstName
                        </label>
                        <div className="mt-1">
                          <input
                            type="text"
                            name="firstName"
                            value={inputs?.firstName}
                            onChange={handleChange}
                            id="firstName"
                            className="block px-4 w-full h-8 rounded-md border-2 border-gray-400 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
                            placeholder="Enter player firstName"
                          />
                        </div>
                      </div>

                      <div className="text-left w-full">
                        <label
                          htmlFor="lastName"
                          className="block text-sm font-medium text-gray-700"
                        >
                          lastName
                        </label>
                        <div className="mt-1">
                          <input
                            type="text"
                            name="lastName"
                            value={inputs?.lastName}
                            onChange={handleChange}
                            id="lastName"
                            className="block px-4 w-full h-8 rounded-md border-2 border-gray-400 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
                            placeholder="Enter player last name"
                          />
                        </div>
                      </div>

                      <div className="text-left w-full">
                        <label
                          htmlFor="birthday"
                          className="block text-sm font-medium text-gray-700"
                        >
                          Birthday
                        </label>
                        <div className="mt-1">
                          <input
                            type="date"
                            name="birthday"
                            value={inputs?.birthday}
                            onChange={handleChange}
                            id="birthday"
                            className="block px-4 w-full h-8 rounded-md border-2 border-gray-400 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
                          />
                        </div>
                      </div>

                      <div className="text-left w-full">
                        <label
                          htmlFor="weight"
                          className="block text-sm font-medium text-gray-700"
                        >
                          Weight
                        </label>
                        <div className="mt-1">
                          <input
                            type="number"
                            name="weight"
                            value={inputs?.weight}
                            onChange={handleChange}
                            id="weight"
                            className="block px-4 w-full h-8 rounded-md border-2 border-gray-400 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
                          />
                        </div>
                      </div>

                      <div className="text-left w-full">
                        <label
                          htmlFor="height"
                          className="block text-sm font-medium text-gray-700"
                        >
                          Height
                        </label>
                        <div className="mt-1">
                          <input
                            type="number"
                            name="height"
                            value={inputs?.height}
                            onChange={handleChange}
                            id="height"
                            className="block px-4 w-full h-8 rounded-md border-2 border-gray-400 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
                          />
                        </div>
                      </div>

                      <div className="text-left w-full">
                        <label
                          htmlFor="nationality"
                          className="block text-sm font-medium text-gray-700"
                        >
                          Nationality
                        </label>
                        {!player?.id ? (
                          <select
                            onChange={handleChange}
                            value={inputs.nationality}
                            name="nationality"
                            id="nationality"
                            className="w-full rounded-md"
                          >
                            <option value="">Select a nationality</option>
                            {countries &&
                              countries.sort().map((country, index) => (
                                <option value={country} key={index}>
                                  {country}
                                </option>
                              ))}
                          </select>
                        ) : (
                          <select
                            onChange={handleChange}
                            value={inputs.nationality}
                            name="nationality"
                            id="nationality"
                            className="w-full rounded-md"
                          >
                            <option
                              defaultValue={player.nationality}
                              value={player.nationality}
                            >
                              {player.nationality}
                            </option>
                            {countries &&
                              countries.sort().map((country, index) => (
                                <option value={country} key={index}>
                                  {country}
                                </option>
                              ))}
                          </select>
                        )}
                      </div>
                    </form>
                  </div>
                </div>
                <div className="mt-5 sm:mt-6 sm:grid sm:grid-flow-row-dense sm:grid-cols-2 sm:gap-3">
                  {!player?.id ? (
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

export default PlayerModal;
