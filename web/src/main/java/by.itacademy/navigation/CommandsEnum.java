package by.itacademy.navigation;

import by.itacademy.navigation.impl.AddNewsCommand;
import by.itacademy.navigation.impl.ListNewsCommand;
import by.itacademy.navigation.impl.MainCommand;
import by.itacademy.navigation.impl.RedirectCommand;

public enum CommandsEnum {
    MAIN {
        @Override
        public Command createCommand() {
            return new MainCommand();
        }
    },
    ADD_NEWS {
        @Override
        public Command createCommand() {
            return new AddNewsCommand();
        }
    },
    LIST_NEWS {
        @Override
        public Command createCommand() {
            return new ListNewsCommand();
        }
    },
    MAIN_REDIRECT {
        @Override
        public Command createCommand() {
            return new RedirectCommand();
        }
    };

    public abstract Command createCommand();
}
