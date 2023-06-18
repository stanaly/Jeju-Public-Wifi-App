import { createBrowserRouter, RouterProvider } from "react-router-dom";
import { CookiesProvider } from "react-cookie";
import Main from "./main";
import Login from "./login";
import Register from "./register";
import Splash from "./splash";
import React from "react";
import ReactDOM from "react-dom";

import "./index.css";

const router = createBrowserRouter([
  {
    children: [
      {
        path: "/",
        element: <Splash />,
      },
      {
        path: "main",
        element: <Main />,
        children: [],
      },
      {
        path: "login",
        element: <Login />,
      },
      {
        path: "register",
        element: <Register />,
      },
    ],
  },
]);

ReactDOM.render(
  <React.StrictMode>
    <CookiesProvider>
      <RouterProvider router={router} />
    </CookiesProvider>
  </React.StrictMode>,
  document.getElementById("root")
);
