import notFoundImg from "../assets/undraw_under_construction_-46-pa.svg";
const NotFound = () => {
  return (
    <div className="h-screen bg-white flex items-center justify-center gap-6 flex-col-reverse md:flex-row md:justify-start">
      <div className="w-full md:w-1/3 text-center md:text-left">
        <h1 className="text-9xl text-gray-900 font-serif font-semibold">404</h1>
        <p className="text-4xl text-gray-800 mb-4">We sincerely apologize.</p>
        <p className="text-2xl text-gray-600">
          The page you are looking for does not exist. Go back to the{" "}
          <a href="/" className="underline font-semibold">
            homepage
          </a>
        </p>
      </div>
      <div className="w-full md:w-2/3 ml-auto opacity-80 relative">
        <img src={notFoundImg} alt="not found img" className="w-full block" />
      </div>
    </div>
  );
};

export default NotFound;
