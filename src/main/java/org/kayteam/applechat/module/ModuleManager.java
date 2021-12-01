package org.kayteam.applechat.module;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager {

    List<Module> modules = new ArrayList<>();

    public boolean addModule(Module module) {
        if (modules.contains(module)) return false;
        return modules.add(module);
    }

    public boolean removeModule(String name) {
        Module module = getModule(name);
        if (module == null) return false;
        return removeModule(module);
    }

    public boolean removeModule(Module module) {
        return modules.remove(module);
    }

    public Module getModule(String name) {
        for (Module module:modules) {
            if (module.getName().equals(name)) return module;
        }
        return null;
    }

    public List<Module> getModules() { return modules; }

    public void clearModules() {
        modules.clear();
    }

}