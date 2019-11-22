package view;

import controller.interfaces.Language;

/**
 * Allows for classes to implement this one interface and have methods from ConfigurationView, MovementView, StaticView and Language defined.
 *
 * @author Goh Khian Wei
 * @author Ben Lawrence
 * @author Joshua Medway
 */
public interface BasicView extends ConfigurationView, MovementView, StaticView, Language {
}
