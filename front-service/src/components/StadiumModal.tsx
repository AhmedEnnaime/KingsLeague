import { Dialog, Transition } from "@headlessui/react";
import { Fragment, useRef, useState } from "react";
import IStadium from "../interfaces/IStadium";
import { StadiumModalProps } from "../propsTypes/StadiumModalProps";
import { toast } from "react-toastify";
import { createStadium, updateStadium } from "../redux/stadiums/stadiumActions";
import { useAppDispatch } from "../redux/store";

const StadiumModal = ({ open, setOpen, stadium }: StadiumModalProps) => {
  const cancelButtonRef = useRef(null);
  const dispatch = useAppDispatch();
  const [inputs, setInputs] = useState<IStadium>({
    name: stadium?.name ?? "",
    location: stadium?.location ?? "",
    capacity: stadium?.capacity ?? 0,
  });
  const handleAddChange = (
    e:
      | React.ChangeEvent<HTMLInputElement>
      | React.ChangeEvent<HTMLTextAreaElement>
  ) => {
    setInputs((prevState) => ({
      ...prevState,
      [e.target.name]: e.target.value,
    }));
  };

  const handleAddSubmit = async (e: React.FormEvent<EventTarget>) => {
    e.preventDefault();
    dispatch(createStadium(inputs))
      .then(() => {
        toast.success("Stadium created successfully");
        setOpen(false);
      })
      .catch((err) => {
        console.error("Failed to create stadium:", err);
        toast.error("Failed to create stadium");
      });
  };

  const handleUpdateSubmit = async (e: React.FormEvent<EventTarget>) => {
    e.preventDefault();
    const { id } = stadium as IStadium;
    const updatedStadiumData = inputs;
    const stadiumID = id as number;
    dispatch(updateStadium({ id: stadiumID, stadiumData: updatedStadiumData }))
      .then(() => {
        toast.success("Stadium updated successfully");
        setOpen(false);
      })
      .catch((err) => {
        console.error("Failed to update stadium:", err);
        toast.error("Failed to update stadium");
      });
  };

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
                      Fill stadium's information
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
                            onChange={handleAddChange}
                            id="name"
                            className="block px-4 w-full h-8 rounded-md border-2 border-gray-400 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
                            placeholder="Enter stadium's name"
                          />
                        </div>
                      </div>

                      <div className="text-left w-full">
                        <label
                          htmlFor="location"
                          className="block text-sm font-medium text-gray-700"
                        >
                          Location
                        </label>
                        <div className="mt-1">
                          <input
                            type="text"
                            name="location"
                            value={inputs?.location}
                            onChange={handleAddChange}
                            id="location"
                            className="block px-4 w-full h-8 rounded-md border-2 border-gray-400 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
                            placeholder="Enter stadium's location"
                          />
                        </div>
                      </div>

                      <div className="text-left w-full">
                        <label
                          htmlFor="capacity"
                          className="block text-sm font-medium text-gray-700"
                        >
                          Capacity
                        </label>
                        <div className="mt-1">
                          <input
                            type="number"
                            name="capacity"
                            value={inputs?.capacity}
                            onChange={handleAddChange}
                            id="capacity"
                            className="block px-4 w-full h-8 rounded-md border-2 border-gray-400 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
                          />
                        </div>
                      </div>
                    </form>
                  </div>
                </div>
                <div className="mt-5 sm:mt-6 sm:grid sm:grid-flow-row-dense sm:grid-cols-2 sm:gap-3">
                  {!stadium?.id ? (
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

export default StadiumModal;
