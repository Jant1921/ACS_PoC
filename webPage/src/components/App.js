import React from 'react';
import {Link} from 'react-router';

/**
 * <App />
 * Wraps all our child components to provide a global navigation.
 * This makes it simple to have a component at the index '/' route
 * of our application.
 */
const Core = ({ children }) =>
  <div className="app">
      { children }
  </div>;

export default Core;
