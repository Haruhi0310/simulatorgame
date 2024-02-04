/*
 * MainObjectFactory class:
 *  - Superclass of MainObjectConcreteFactory
 *  - Holds abstract methods to create main objects and buttons.
 */

package store;

import button.Button;

public abstract class MainObjectFactory {
	public abstract MainObject createMainObject(String type);
	public abstract Button createButton(String type);
}
